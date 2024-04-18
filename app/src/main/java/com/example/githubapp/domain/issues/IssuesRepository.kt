package com.example.githubapp.domain.issues

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.githubapp.data.issues.IssuesDatasource
import com.example.githubapp.domain.helpers.SimplePaginationSource
import com.example.githubapp.domain.helpers.toEither
import com.example.githubapp.domain.issues.mappers.IssueMapper
import com.example.githubapp.domain.models.PagedData
import javax.inject.Inject

class IssuesRepository @Inject constructor(
    private val datasource: IssuesDatasource,
    private val issueMapper: IssueMapper,
) {

    private companion object {
        const val PAGE_SIZE_UNSTABLE = 1
    }

    fun getAuthenticatedUserIssues() = Pager(
        config = PagingConfig(PAGE_SIZE_UNSTABLE),
        pagingSourceFactory = { SimplePaginationSource { page -> getAuthenticatedUserIssues(page) } },
    ).flow

    private suspend fun getAuthenticatedUserIssues(page: Int) =
        datasource.getIssuesAndPullRequestsAssignedToUser(page).toEither { response ->
            PagedData(
                isLastPage = response.isEmpty(),
                items = response
                    .filter { issuesAndPullRequests -> issuesAndPullRequests.pullRequestLinksResponse == null }
                    .map { issueResponse -> issueMapper.map(issueResponse) }
            )
        }
}