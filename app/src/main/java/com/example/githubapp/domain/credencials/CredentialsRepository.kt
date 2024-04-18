package com.example.githubapp.domain.credencials

import com.example.githubapp.data.credencials.CredentialsDatasource
import com.example.githubapp.data.managers.ExpiredCredentialsCallback
import com.example.githubapp.data.managers.UserTokenManager
import com.example.githubapp.domain.helpers.toEither
import javax.inject.Inject

class CredentialsRepository @Inject constructor(
    private val datasource: CredentialsDatasource,
    private val tokenManager: UserTokenManager,
    private val badCredentialsCallback: ExpiredCredentialsCallback,
) {

    suspend fun login(code: String) =
        datasource.authenticateUser(code).toEither {}

    fun isUserLoggedIn() = tokenManager.getAccessToken() != null

    fun getUserLoggedOffNotifier() = badCredentialsCallback.getExpiredCredentialsChanel()
}
