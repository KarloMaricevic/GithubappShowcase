package com.example.githubapp.domain.profile.usecase

import com.example.githubapp.domain.credencials.CredentialsRepository
import javax.inject.Inject

class Logout @Inject constructor(
    private val repository: CredentialsRepository
) {

    operator fun invoke() = repository.logout()
}
