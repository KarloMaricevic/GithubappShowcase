package com.example.githubapp.domain.helpers

import arrow.core.Either
import com.example.githubapp.core.models.Failure
import com.example.githubapp.data.models.NetworkResource

fun <T> NetworkResource<T>.toEither() = when (this) {
    is NetworkResource.Success -> Either.Right(value)
    is NetworkResource.GenericError -> Either.Left(
        Failure.ErrorMessage(cause.message ?: "Generic error")
    )

    is NetworkResource.HttpError -> Either.Left(Failure.ErrorMessage(message))
    is NetworkResource.NetworkError -> Either.Left(Failure.ErrorMessage("Network error"))
}

fun <T, R> NetworkResource<T>.toEither(valueMapper: (T) -> R) = when (this) {
    is NetworkResource.Success -> Either.Right(valueMapper(value))
    is NetworkResource.GenericError -> Either.Left(
        Failure.ErrorMessage(cause.message ?: "Generic error")
    )

    is NetworkResource.HttpError -> Either.Left(Failure.ErrorMessage(message))
    is NetworkResource.NetworkError -> Either.Left(Failure.ErrorMessage("Network error"))
}
