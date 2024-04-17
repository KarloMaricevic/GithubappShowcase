package com.example.githubapp.domain.credencials.usecase

import com.example.githubapp.data.ApiConstants
import com.example.githubapp.domain.credencials.models.AuthLink
import java.util.UUID
import javax.inject.Inject

class GenerateLoginLink @Inject constructor() {

    private companion object {
        const val REDIRECT_KEY_URL = "redirect_url"
        const val CLIENT_ID_KEY = "client_id"
        const val SCOPE_KEY = "scope"
        const val STATE_KEY = "state"
        const val DEFAULT_SCOPE = "user,repo"
    }

    operator fun invoke(): AuthLink {
        val state = UUID.randomUUID().toString()
        return AuthLink(
            url = "${ApiConstants.BASE_LOGIN_PAGE_URL}?$CLIENT_ID_KEY=${ApiConstants.CLIENT_ID}" +
                    "&$REDIRECT_KEY_URL=${ApiConstants.BASE_AUTH_REDIRECT_URL}" +
                    "&$SCOPE_KEY=${DEFAULT_SCOPE}" +
                    "&$STATE_KEY=$state",
            state = state,
            baseRedirectUrl = ApiConstants.BASE_AUTH_REDIRECT_URL,
        )
    }
}
