package com.example.githubapp.data.interceptors

import com.example.githubapp.data.credencials.CredentialsDatasource
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AccessForbiddenAuthInterceptor @Inject constructor(
    private val credentialsDatasource: CredentialsDatasource,
) : Interceptor {

    private companion object {
        const val ACCESS_FORBIDDEN_CODE = 403
        const val REQUIRES_AUTH_CODE = 401
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        if (response.code == ACCESS_FORBIDDEN_CODE || response.code == REQUIRES_AUTH_CODE) {
            credentialsDatasource.notifyCallFailedBecauseOfBadAuth()
        }
        return response
    }
}
