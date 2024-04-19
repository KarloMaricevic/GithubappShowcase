package com.example.githubapp.domain.repository

import com.example.githubapp.data.repositoryDetails.RepositoryDatasource
import com.example.githubapp.domain.helpers.toEither
import com.example.githubapp.domain.search.mappers.RepositoryMapper
import javax.inject.Inject

class RepositoriesRepository @Inject constructor(
    private val datasource: RepositoryDatasource,
    private val mapper: RepositoryMapper,
) {

    suspend fun getRepositoryDetails(owner: String, repoName: String) =
        datasource.getRepositoryDetails(owner = owner, repoName = repoName)
            .toEither { content -> mapper.map(content) }

    suspend fun isStarred(owner: String, repoName: String) =
        datasource.isStarred(owner = owner, repoName = repoName).toEither()

    suspend fun star(owner: String, repoName: String) =
        datasource.star(owner = owner, repoName = repoName).toEither()

    suspend fun unStar(owner: String, repoName: String) =
        datasource.unStar(owner = owner, repoName = repoName).toEither()
}
