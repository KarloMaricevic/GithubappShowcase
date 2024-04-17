package com.example.githubapp.domain.profile.usecase

import com.example.githubapp.domain.profile.ProfileRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class GetAuthenticatedUsersProfileImpl @Inject constructor(
    private val repository: ProfileRepository,
) : GetAuthenticatedUsersProfile {

    override suspend operator fun invoke() =
        repository.getAuthenticatedUserProfile()
}
