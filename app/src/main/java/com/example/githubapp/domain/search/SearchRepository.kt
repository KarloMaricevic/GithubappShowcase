package com.example.githubapp.domain.search

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.githubapp.data.search.SearchDatasource
import com.example.githubapp.domain.helpers.SimplePaginationSource
import com.example.githubapp.domain.helpers.toResult
import com.example.githubapp.domain.models.PagedData
import com.example.githubapp.domain.search.mappers.RepositoryMapper
import javax.inject.Inject

class SearchRepository @Inject constructor(
    private val datasource: SearchDatasource,
    private val repositoryMapper: RepositoryMapper,
) {

    private companion object {
        const val PAGE_SIZE = 20
    }

    private suspend fun getRepositories(
        query: String,
        page: Int,
    ) = datasource.getRepositories(query, page).toResult { content ->
        PagedData(
            isLastPage = content.items.isEmpty(),
            items = content.items.map { item -> repositoryMapper.map(item) }
        )
    }

    fun getRepositories(query: String) = Pager(
        config = PagingConfig(PAGE_SIZE),
        pagingSourceFactory = { SimplePaginationSource { page -> getRepositories(query, page) } }
    ).flow
}
