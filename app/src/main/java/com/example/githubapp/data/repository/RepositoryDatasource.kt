package com.example.githubapp.data.repositoryDetails

import com.example.githubapp.data.helpers.safeApiCall
import com.example.githubapp.data.repository.RepositoryApi
import retrofit2.HttpException
import javax.inject.Inject

class RepositoryDatasource @Inject constructor(
    private val api: RepositoryApi,
) {

    private companion object {
        private const val REPOSITORY_STARRED_BY_USER_CODE = 204
        private const val SUCCESSFUL_STARRED_OR_UN_STARRED_FOR_USER_CODE = 204
    }

    suspend fun getRepositoryDetails(owner: String, repoName: String) =
        safeApiCall { api.getDetails(owner = owner, repoName = repoName) }

    suspend fun isStarred(owner: String, repoName: String) = safeApiCall {
        val response = api.isStarred(owner = owner, repoName = repoName)
        when (response.code()) {
            REPOSITORY_STARRED_BY_USER_CODE -> true
            404 -> false
            else -> throw HttpException(response)
        }
    }

    suspend fun star(owner: String, repoName: String) = safeApiCall {
        val response = api.star(owner = owner, repoName = repoName)
        when (response.code()) {
            SUCCESSFUL_STARRED_OR_UN_STARRED_FOR_USER_CODE -> true
            else -> throw HttpException(response)
        }
    }

    suspend fun unStar(owner: String, repoName: String) = safeApiCall {
        val response = api.unStar(owner = owner, repoName = repoName)
        when (response.code()) {
            SUCCESSFUL_STARRED_OR_UN_STARRED_FOR_USER_CODE -> true
            else -> throw HttpException(response)
        }
    }
}
