package com.example.githubapp.domain.repository.usecase

import com.example.githubapp.domain.repository.RepositoriesRepository
import javax.inject.Inject

class UnStarRepository @Inject constructor(
    private val repository: RepositoriesRepository,
) {

    suspend operator fun invoke(owner: String, repoName: String) =
        repository.unStar(owner = owner, repoName = repoName)
}
