package com.example.githubapp.feature.profile.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.githubapp.R
import com.example.githubapp.core.base.BaseViewModel
import com.example.githubapp.core.base.TIMEOUT_DELAY
import com.example.githubapp.core.components.SimpleDialogUI
import com.example.githubapp.core.dictionary.Dictionary
import com.example.githubapp.core.navigation.NavigationEvent
import com.example.githubapp.core.navigation.Navigator
import com.example.githubapp.core.system.SystemCall
import com.example.githubapp.domain.models.Profile
import com.example.githubapp.domain.profile.usecase.GetAuthenticatedUsersProfile
import com.example.githubapp.domain.profile.usecase.Logout
import com.example.githubapp.feature.home.navigation.HomeScreenRouter
import com.example.githubapp.feature.login.navigation.LoginScreenRouter
import com.example.githubapp.feature.profile.models.ProfileScreenEvent
import com.example.githubapp.feature.profile.models.ProfileScreenEvent.OnLogoutCancel
import com.example.githubapp.feature.profile.models.ProfileScreenEvent.OnLogoutClicked
import com.example.githubapp.feature.profile.models.ProfileScreenEvent.OnLogoutConfirmation
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
    private val navigator: Navigator,
    private val dictionary: Dictionary,
    private val logout: Logout,
) : BaseViewModel<ProfileScreenEvent>() {

    private val profile = MutableStateFlow<Profile?>(null)
    private val isLoading = MutableStateFlow(true)
    private val isError = MutableStateFlow(false)
    private val dialog = MutableStateFlow<SimpleDialogUI?>(null)

    val viewState = combine(
        profile,
        isLoading,
        isError,
        dialog,
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
            is OnShareClicked -> profile.value?.let { notNullProfile ->
                systemCall.share(notNullProfile.url)
            }

            is OnLogoutClicked -> dialog.update { createLogoutDialog() }
            is OnLogoutCancel -> dialog.update { null }
            is OnLogoutConfirmation -> {
                logout()
                viewModelScope.launch {
                    navigator.emitDestination(
                        NavigationEvent.Destination(
                            destination = LoginScreenRouter.route(),
                            builder = {
                                this.popUpTo(HomeScreenRouter.route()) {
                                    this.inclusive = true
                                }
                            },
                        ),
                    )
                }
            }
        }
    }

    private fun createLogoutDialog() = SimpleDialogUI(
        title = dictionary.getString(R.string.profile_screen_log_out_title),
        confirmationText = dictionary.getString(R.string.confirm),
        onConfirmation = { onEvent(OnLogoutConfirmation) },
        dismissText = dictionary.getString(R.string.cancel),
        onDismiss = { onEvent(OnLogoutCancel) },
    )
}
