package com.example.githubapp.core.dictionary

import androidx.annotation.StringRes

interface Dictionary {
    fun getString(@StringRes id: Int): String
}
