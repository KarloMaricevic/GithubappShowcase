package com.example.githubapp.feature.profile.models

sealed interface ProfileScreenEvent {

    data object OnReloadClicked : ProfileScreenEvent

    data object OnShareClicked : ProfileScreenEvent
}
