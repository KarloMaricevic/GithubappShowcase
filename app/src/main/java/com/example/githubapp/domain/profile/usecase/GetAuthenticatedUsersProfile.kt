package com.example.githubapp.domain.profile.usecase

import arrow.core.Either
import com.example.githubapp.core.models.Failure
import com.example.githubapp.domain.models.Profile

interface GetAuthenticatedUsersProfile {

    suspend operator fun invoke(): Either<Failure, Profile>
}
