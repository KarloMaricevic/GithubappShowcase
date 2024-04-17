package com.example.githubapp.feature.login.models

sealed interface LoginScreenEvent {

    data class OnRedirect(val redirectUrl: String) : LoginScreenEvent
}
