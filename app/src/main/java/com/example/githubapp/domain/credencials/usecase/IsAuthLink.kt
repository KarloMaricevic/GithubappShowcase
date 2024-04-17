package com.example.githubapp.domain.credencials.usecase

import com.example.githubapp.data.ApiConstants
import com.example.githubapp.domain.credencials.models.AuthLink
import javax.inject.Inject

class IsAuthLink @Inject constructor() {

    private companion object {
        const val STATE_PARAM_NAME = "state"
        const val PARAM_VALUE_DELIMITER = "="
    }

    operator fun invoke(redirectUrl: String, authLink: AuthLink): Boolean {
        val indexOfStateParam = redirectUrl.indexOf(STATE_PARAM_NAME)
        val indexOfStateValue =
            indexOfStateParam + STATE_PARAM_NAME.length + PARAM_VALUE_DELIMITER.length
        val redirectUrlState = redirectUrl.substring(indexOfStateValue)
        return redirectUrl.startsWith(ApiConstants.BASE_AUTH_REDIRECT_URL) && redirectUrlState == authLink.state
    }
}
