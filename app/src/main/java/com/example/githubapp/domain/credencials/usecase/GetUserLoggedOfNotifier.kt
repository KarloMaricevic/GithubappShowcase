package com.example.githubapp.domain.credencials.usecase

import com.example.githubapp.data.credencials.CredentialsDatasource
import javax.inject.Inject

class GetUserLoggedOfNotifier @Inject constructor(
    private val credentialsDatasource: CredentialsDatasource,
) {

    operator fun invoke() = credentialsDatasource.getBadAuthChanel()
}
