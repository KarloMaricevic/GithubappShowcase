package com.example.githubapp.feature.profile.models

import com.example.githubapp.domain.models.Profile

data class ProfileScreenState(
    val profile: Profile? = null,
    val isLoading: Boolean = true,
    val isError: Boolean = false,
)
