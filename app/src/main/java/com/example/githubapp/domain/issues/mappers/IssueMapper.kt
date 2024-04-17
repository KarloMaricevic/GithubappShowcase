package com.example.githubapp.domain.issues.mappers

import com.example.githubapp.data.models.AuthorInfoResponse
import com.example.githubapp.data.models.IssueResponse
import com.example.githubapp.domain.models.AuthorInfo
import com.example.githubapp.domain.models.Image
import com.example.githubapp.domain.models.Issue
import com.example.githubapp.domain.models.Issue.State
import com.example.githubapp.domain.search.mappers.RepositoryMapper
import javax.inject.Inject

class IssueMapper @Inject constructor(
    private val repositoryMapper: RepositoryMapper,
) {

    private companion object {
        const val RESPONSE_STATUS_OPEN = "open"
        const val RESPONSE_STATUS_CLOSED = "closed"
    }

    fun map(response: IssueResponse) = Issue(
        id = response.id,
        repoName = response.repositoryUrl,
        title = response.title,
        authorInfo = mapAuthorInfo(response.author),
        description = response.body,
        status = when (response.state) {
            RESPONSE_STATUS_OPEN -> State.OPEN
            RESPONSE_STATUS_CLOSED -> State.CLOSED
            else -> State.CLOSED
        },
        repository = repositoryMapper.map(response.repository),
    )

    private fun mapAuthorInfo(response: AuthorInfoResponse) = AuthorInfo(
        username = response.username,
        avatar = Image.RemoteImage(response.avatarUrl),
    )
}
