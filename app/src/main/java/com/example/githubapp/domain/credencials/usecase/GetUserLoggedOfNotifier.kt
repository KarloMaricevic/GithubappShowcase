package com.example.githubapp.domain.credencials.usecase

import com.example.githubapp.domain.credencials.CredentialsRepository
import javax.inject.Inject

class GetUserLoggedOfNotifier @Inject constructor(
    private val repository: CredentialsRepository,
) {

    operator fun invoke() = repository.getUserLoggedOffNotifier()
}
