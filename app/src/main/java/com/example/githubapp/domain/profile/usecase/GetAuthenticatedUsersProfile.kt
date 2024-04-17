package com.example.githubapp.domain.profile.usecase

import com.example.githubapp.domain.models.Profile

interface GetAuthenticatedUsersProfile {

    suspend operator fun invoke(): Result<Profile>
}
