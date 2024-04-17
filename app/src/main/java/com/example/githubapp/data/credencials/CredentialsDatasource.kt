package com.example.githubapp.data.credencials

import com.example.githubapp.data.helpers.safeApiCall
import com.example.githubapp.data.managers.UserTokenManager
import com.example.githubapp.data.models.AccessTokenResponse
import com.example.githubapp.data.models.NetworkResource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CredentialsDatasource @Inject constructor(
    private val credentialsApi: CredentialsApi,
    private val tokenManager: UserTokenManager,
) {

    suspend fun authenticateUser(code: String): NetworkResource<AccessTokenResponse> = safeApiCall {
        val responses = credentialsApi.getAccessToken(code)
        tokenManager.saveAccessToken(responses.token)
        responses
    }
}
