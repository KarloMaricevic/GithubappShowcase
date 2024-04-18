package com.example.githubapp.feature.profile.models

sealed interface ProfileScreenEvent {

    data object OnReloadClicked : ProfileScreenEvent

    data object OnShareClicked : ProfileScreenEvent

    data object OnLogoutClicked : ProfileScreenEvent

    data object OnLogoutConfirmation : ProfileScreenEvent

    data object OnLogoutCancel : ProfileScreenEvent
}
