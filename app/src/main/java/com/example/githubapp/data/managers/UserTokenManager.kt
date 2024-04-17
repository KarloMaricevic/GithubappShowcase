package com.example.githubapp.data.managers

import com.example.githubapp.core.sharedPrefs.SharedPrefs
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserTokenManager @Inject constructor(
    private val sharedPrefs: SharedPrefs,
) {

    private companion object {
        const val ACCESS_TOKEN_SHARED_PREFS_KEY = "access_token"
    }

    private var cachedToken: String? = null

    fun getAccessToken() = if (cachedToken != null) {
        cachedToken
    } else {
        cachedToken = sharedPrefs.getString(ACCESS_TOKEN_SHARED_PREFS_KEY)
        cachedToken
    }

    fun saveAccessToken(token: String) {
        sharedPrefs.putString(ACCESS_TOKEN_SHARED_PREFS_KEY, token)
        cachedToken = token
    }
}
