package com.example.githubapp.core.navigation

import com.example.githubapp.core.dispatchers.DefaultDispatcher
import com.example.githubapp.domain.credencials.usecase.GetUserLoggedOfNotifier
import com.example.githubapp.feature.home.navigation.HomeScreenRouter
import com.example.githubapp.feature.login.navigation.LoginScreenRouter
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class NavigatorImpl @Inject constructor(
    getUserLoggedOfNotifier: GetUserLoggedOfNotifier,
    @DefaultDispatcher dispatcher: CoroutineDispatcher
) : Navigator {

    private val _navigationEvent = Channel<NavigationEvent>(Channel.BUFFERED)
    override val navigationEvent = _navigationEvent.receiveAsFlow()

    init {
        CoroutineScope(dispatcher).launch {
            getUserLoggedOfNotifier().collectLatest {
                _navigationEvent.send(
                    NavigationEvent.Destination(
                        LoginScreenRouter.route(),
                        builder = {
                            this.popUpTo(HomeScreenRouter.route()) {
                                this.inclusive = true
                            }
                        }
                    )
                )
            }
        }
    }

    override suspend fun emitDestination(navigationEvent: NavigationEvent) {
        _navigationEvent.send(navigationEvent)
    }
}
