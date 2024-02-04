package com.example.githubapp.core.navigation

import android.content.Intent
import androidx.navigation.NavOptionsBuilder

sealed interface NavigationEvent {
    object NavigateUp : NavigationEvent
    object NavigateBack : NavigationEvent
    data class Destination(
        val destination: String,
        val builder: NavOptionsBuilder.() -> Unit = {},
    ) : NavigationEvent

    data class OpenExternalDestination(
        val intent: Intent
    ) : NavigationEvent
}
