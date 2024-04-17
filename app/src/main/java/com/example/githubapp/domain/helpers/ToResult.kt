package com.example.githubapp.domain.helpers

import com.example.githubapp.data.models.NetworkResource

fun <T> NetworkResource<T>.toResult(): Result<T> = when (this) {
    is NetworkResource.Success -> Result.success(value)
    is NetworkResource.GenericError -> Result.failure(cause)
    is NetworkResource.HttpError -> Result.failure(Throwable(message))
    is NetworkResource.NetworkError -> Result.failure(Throwable(this.toString()))
}

fun <T, R> NetworkResource<T>.toResult(valueMapper: (T) -> R) = when (this) {
    is NetworkResource.Success -> Result.success(valueMapper(value))
    is NetworkResource.GenericError -> Result.failure(cause)
    is NetworkResource.HttpError -> Result.failure(Throwable(message))
    is NetworkResource.NetworkError -> Result.failure(Throwable(this.toString()))
}
