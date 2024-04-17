package com.example.githubapp.data.managers

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ExpiredCredentialsCallback @Inject constructor() {

    private val badAuthNotificationChannel = Channel<Unit>()

    fun expiredCredentialsError() {
        badAuthNotificationChannel.trySend(Unit)
    }

    fun getExpiredCredentialsChanel() = badAuthNotificationChannel.receiveAsFlow()
}
