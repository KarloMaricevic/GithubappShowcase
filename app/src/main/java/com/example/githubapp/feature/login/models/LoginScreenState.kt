package com.example.githubapp.feature.login.models

data class LoginScreenState(
    val url: String,
    val isLoading: Boolean = false,
    val isError: Boolean = false,
)
