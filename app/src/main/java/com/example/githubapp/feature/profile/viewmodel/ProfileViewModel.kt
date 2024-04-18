package com.example.githubapp.feature.profile.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.githubapp.core.base.BaseViewModel
import com.example.githubapp.core.base.TIMEOUT_DELAY
import com.example.githubapp.core.system.SystemCall
import com.example.githubapp.domain.models.Profile
import com.example.githubapp.domain.profile.usecase.GetAuthenticatedUsersProfile
import com.example.githubapp.feature.profile.models.ProfileScreenEvent
import com.example.githubapp.feature.profile.models.ProfileScreenEvent.OnReloadClicked
import com.example.githubapp.feature.profile.models.ProfileScreenEvent.OnShareClicked
import com.example.githubapp.feature.profile.models.ProfileScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getProfile: GetAuthenticatedUsersProfile,
    private val systemCall: SystemCall,
) : BaseViewModel<ProfileScreenEvent>() {

    private val profile = MutableStateFlow<Profile?>(null)
    private val isLoading = MutableStateFlow(true)
    private val isError = MutableStateFlow(false)

    val viewState = combine(
        profile,
        isLoading,
        isError,
        ::ProfileScreenState,
    ).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(TIMEOUT_DELAY),
        initialValue = ProfileScreenState(),
    )

    init {
        loadUsersProfile()
    }

    private fun loadUsersProfile() {
        isLoading.update { true }
        isError.update { false }
        viewModelScope.launch {
            getProfile().fold(
                {
                    isLoading.update { false }
                    isError.update { true }
                },
                { profile ->
                    isLoading.update { false }
                    this@ProfileViewModel.profile.update { profile }
                },
            )
        }
    }

    override fun onEvent(event: ProfileScreenEvent) {
        when (event) {
            is OnReloadClicked -> loadUsersProfile()
            OnShareClicked -> profile.value?.let { notNullProfile ->
                systemCall.share(notNullProfile.url)
            }
        }
    }
}
