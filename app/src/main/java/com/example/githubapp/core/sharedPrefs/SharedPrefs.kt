package com.example.githubapp.core.sharedPrefs

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPrefs @Inject constructor(
    @ApplicationContext appContext: Context
) {

    private companion object {
        const val SHARE_PREFS_NAME = "prefs"
    }

    private val sharedPreferences: SharedPreferences =
        appContext.getSharedPreferences(SHARE_PREFS_NAME, Context.MODE_PRIVATE)


    fun putString(
        key: String,
        value: String?,
    ): Boolean {
        return sharedPreferences.edit().putString(key, value).commit()
    }

    fun getString(
        key: String,
    ): String? = sharedPreferences.getString(key, null)
}
