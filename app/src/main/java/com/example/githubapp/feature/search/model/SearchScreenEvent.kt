package com.example.githubapp.feature.search.model

sealed interface SearchScreenEvent {

    data class OnSearchTextChanged(val text: String) : SearchScreenEvent

    data object OnOpenFiltersClicked : SearchScreenEvent

    data class OnRepositoryClicked(
        val owner: String,
        val repoName: String,
    ) : SearchScreenEvent
}
