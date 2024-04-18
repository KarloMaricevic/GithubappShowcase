package com.example.githubapp.domain.helpers

import androidx.paging.PagingSource
import androidx.paging.PagingSource.LoadResult.Error
import androidx.paging.PagingSource.LoadResult.Page
import androidx.paging.PagingState
import arrow.core.Either
import com.example.githubapp.core.models.Failure
import com.example.githubapp.core.models.foldToString
import com.example.githubapp.domain.models.PagedData

class SimplePaginationSource<T : Any>(
    private val source: suspend (page: Int) -> Either<Failure, PagedData<T>>,
) : PagingSource<Int, T>() {

    private companion object {
        const val FIRST_PAGE = 1
    }

    override fun getRefreshKey(state: PagingState<Int, T>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, T> {
        val page = params.key ?: FIRST_PAGE
        return source(page).fold(
            { failure -> Error(Throwable(failure.foldToString())) },
            { content ->
                Page(
                    data = content.items,
                    prevKey = if (page == FIRST_PAGE) null else page.minus(1),
                    nextKey = if (!content.isLastPage) page.plus(1) else null,
                )
            },
        )
    }
}
