package com.example.githubapp.feature.login.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.githubapp.core.base.BaseViewModel
import com.example.githubapp.core.navigation.NavigationEvent
import com.example.githubapp.core.navigation.Navigator
import com.example.githubapp.domain.credencials.usecase.AuthenticateUser
import com.example.githubapp.domain.credencials.usecase.ExtractClientCode
import com.example.githubapp.domain.credencials.usecase.GenerateLoginLink
import com.example.githubapp.domain.credencials.usecase.IsAuthLink
import com.example.githubapp.feature.home.navigation.HomeScreenRouter
import com.example.githubapp.feature.login.models.LoginScreenEvent
import com.example.githubapp.feature.login.models.LoginScreenState
import com.example.githubapp.feature.login.navigation.LoginScreenRouter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val isAuthLink: IsAuthLink,
    private val extractClientCode: ExtractClientCode,
    private val authenticateUser: AuthenticateUser,
    private val navigator: Navigator,
    generateLoginLink: GenerateLoginLink,
) : BaseViewModel<LoginScreenEvent>() {

    private val authLink = generateLoginLink()

    val state = MutableStateFlow(LoginScreenState(authLink.url))

    override fun onEvent(event: LoginScreenEvent) {
        when (event) {
            is LoginScreenEvent.OnRedirect -> {
                if (isAuthLink(event.redirectUrl, authLink)) {
                    val code = extractClientCode(event.redirectUrl)
                    viewModelScope.launch {
                        state.update { state -> state.copy(isLoading = true) }
                        authenticateUser(code).fold(
                            {
                                state.update { state ->
                                    state.copy(
                                        isLoading = false,
                                        isError = true,
                                    )
                                }
                            },
                            {
                                navigator.emitDestination(
                                    NavigationEvent.Destination(
                                        HomeScreenRouter.route()
                                    ) {
                                        popUpTo(LoginScreenRouter.route()) {
                                            inclusive = true
                                        }
                                        launchSingleTop = true
                                    }
                                )
                            },
                        )
                    }
                } else {
                    state.update { it.copy(url = event.redirectUrl) }
                }
            }
        }
    }
}
