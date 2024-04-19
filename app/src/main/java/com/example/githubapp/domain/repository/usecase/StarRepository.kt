package com.example.githubapp.domain.repository.usecase

import com.example.githubapp.domain.repository.RepositoriesRepository
import javax.inject.Inject

class StarRepository @Inject constructor(
    private val repository: RepositoriesRepository,
) {

    suspend operator fun invoke(owner: String, repoName: String) =
        repository.star(owner = owner, repoName = repoName)
}
