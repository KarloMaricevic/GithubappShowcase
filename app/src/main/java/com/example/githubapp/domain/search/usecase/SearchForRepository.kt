package com.example.githubapp.domain.search.usecase

import com.example.githubapp.domain.search.SearchRepository
import javax.inject.Inject

class SearchForRepository @Inject constructor(
    private val repository: SearchRepository,
) {

    operator fun invoke(query: String) = repository.getRepositories(query)
}
