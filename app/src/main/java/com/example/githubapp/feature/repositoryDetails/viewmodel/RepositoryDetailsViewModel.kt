package com.example.githubapp.feature.repositoryDetails.viewmodel

import androidx.lifecycle.viewModelScope
import arrow.core.Either
import com.example.githubapp.R
import com.example.githubapp.core.base.BaseViewModel
import com.example.githubapp.core.base.TIMEOUT_DELAY
import com.example.githubapp.core.components.SimpleDialogUI
import com.example.githubapp.core.dictionary.Dictionary
import com.example.githubapp.core.navigation.NavigationEvent.NavigateBack
import com.example.githubapp.core.navigation.Navigator
import com.example.githubapp.core.system.SystemCall
import com.example.githubapp.domain.repository.usecase.GetRepository
import com.example.githubapp.domain.repository.usecase.IsRepositoryStarredByUser
import com.example.githubapp.domain.repository.usecase.StarRepository
import com.example.githubapp.domain.repository.usecase.UnStarRepository
import com.example.githubapp.domain.search.models.Repository
import com.example.githubapp.feature.repositoryDetails.models.RepositoryDetailsScreenEvent
import com.example.githubapp.feature.repositoryDetails.models.RepositoryDetailsScreenEvent.OnBackClicked
import com.example.githubapp.feature.repositoryDetails.models.RepositoryDetailsScreenEvent.OnReloadClicked
import com.example.githubapp.feature.repositoryDetails.models.RepositoryDetailsScreenEvent.OnStarButtonClicked
import com.example.githubapp.feature.repositoryDetails.models.RepositoryDetailsScreenState
import com.example.githubapp.feature.repositoryDetails.models.RepositoryDetailsVMParam
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = RepositoryDetailsViewModel.RepositoryDetailsViewModelFactory::class)
class RepositoryDetailsViewModel @AssistedInject constructor(
    @Assisted private val params: RepositoryDetailsVMParam,
    private val getRepository: GetRepository,
    private val isRepositoryStarredByUser: IsRepositoryStarredByUser,
    private val starRepository: StarRepository,
    private val unStarRepository: UnStarRepository,
    private val navigator: Navigator,
    private val dictionary: Dictionary,
    private val systemCall: SystemCall,
) : BaseViewModel<RepositoryDetailsScreenEvent>() {

    private val details = MutableStateFlow<Repository?>(null)
    private val isStarredByUser = MutableStateFlow(false)
    private val starLoading = MutableStateFlow(false)
    private val isLoading = MutableStateFlow(true)
    private val dialog = MutableStateFlow<SimpleDialogUI?>(null)

    val viewState = combine(
        details,
        isStarredByUser,
        starLoading,
        isLoading,
        dialog,
        ::RepositoryDetailsScreenState,
    ).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(TIMEOUT_DELAY),
        initialValue = RepositoryDetailsScreenState(),
    )

    init {
        fetchRepoDetails()
    }

    override fun onEvent(event: RepositoryDetailsScreenEvent) {
        when (event) {
            is RepositoryDetailsScreenEvent.OnLinkClicked -> systemCall.openInBrowser(event.url)
            is OnBackClicked -> viewModelScope.launch {
                navigator.emitDestination(NavigateBack)
            }

            is OnReloadClicked -> viewModelScope.launch { fetchRepoDetails() }
            is OnStarButtonClicked -> {
                viewModelScope.launch {
                    starLoading.update { true }
                    if (event.starred) {
                        unStarRepository(params.owner, params.repoName).onRight {
                            isStarredByUser.update { false }
                        }
                    } else {
                        starRepository(params.owner, params.repoName).onRight {
                            isStarredByUser.update { true }
                        }
                    }
                    starLoading.update { false }
                }
            }
        }
    }

    private fun fetchRepoDetails() {
        viewModelScope.launch {
            isLoading.update { true }
            dialog.update { null }
            val repository =
                getRepository(owner = params.owner, repoName = params.repoName)
            val isStarred =
                isRepositoryStarredByUser(owner = params.owner, repoName = params.repoName)
            if (repository is Either.Right && isStarred is Either.Right) {
                isLoading.update { false }
                isStarredByUser.update { isStarred.value }
                details.update { repository.value }
            } else {
                isLoading.update { false }
                dialog.update { createErrorWhileFetchingDetailsDialog() }
            }
        }
    }

    private fun createErrorWhileFetchingDetailsDialog() = SimpleDialogUI(
        title = dictionary.getString(R.string.error),
        confirmationText = dictionary.getString(R.string.try_again),
        onConfirmation = { onEvent(OnReloadClicked) },
        dismissText = dictionary.getString(R.string.go_back),
        onDismiss = { onEvent(OnBackClicked) },
    )

    @AssistedFactory
    interface RepositoryDetailsViewModelFactory {

        fun create(params: RepositoryDetailsVMParam): RepositoryDetailsViewModel
    }
}
