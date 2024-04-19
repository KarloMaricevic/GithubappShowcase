package com.example.githubapp.feature.repositoryDetails.models

sealed interface RepositoryDetailsScreenEvent {

    data class OnLinkClicked(val url: String) : RepositoryDetailsScreenEvent

    data object OnBackClicked : RepositoryDetailsScreenEvent

    data object OnReloadClicked : RepositoryDetailsScreenEvent

    data class OnStarButtonClicked(val starred: Boolean) : RepositoryDetailsScreenEvent
}
