package com.example.githubapp.domain.credencials.models

data class AuthLink(
    val url: String,
    val baseRedirectUrl: String,
    val state: String,
)
