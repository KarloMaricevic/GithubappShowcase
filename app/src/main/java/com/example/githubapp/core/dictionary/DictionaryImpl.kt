package com.example.githubapp.core.dictionary

import android.content.Context
import androidx.annotation.StringRes
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class DictionaryImpl @Inject constructor(
    @ApplicationContext private val appContext: Context,
) : Dictionary {

    override fun getString(@StringRes id: Int) =
        appContext.getString(id)
}
