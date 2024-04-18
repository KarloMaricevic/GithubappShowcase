package com.example.githubapp.core.models

import com.example.githubapp.core.models.Failure.*

sealed interface Failure {
    data class ErrorMessage(val errorMessage: String) : Failure
    data object Unknown : Failure
}

fun Failure.foldToString(): String = when (this) {
    is ErrorMessage -> errorMessage
    is Unknown -> "Unknown error"
}
