package com.example.githubapp.domain.search.mappers

import com.example.githubapp.data.models.AuthorInfoResponse
import com.example.githubapp.data.models.RepositoryResponse
import com.example.githubapp.domain.models.AuthorInfo
import com.example.githubapp.domain.models.Image
import com.example.githubapp.domain.search.models.Repository
import javax.inject.Inject

class RepositoryMapper @Inject constructor() {

    fun map(response: RepositoryResponse) = Repository(
        id = response.id,
        name = response.name,
        author = mapAuthorInfo(response.owner),
        fullName = response.fullName,
        description = response.description,
        language = response.language,
        staredTimes = response.stargazersCount,
        url = response.url,
        forkedTimes = response.forksCount,
    )


    private fun mapAuthorInfo(response: AuthorInfoResponse) =
        AuthorInfo(
            username = response.username,
            avatar = Image.RemoteImage(response.avatarUrl),
        )
}
