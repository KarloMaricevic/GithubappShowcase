package com.example.githubapp.feature.search.model

import androidx.paging.PagingData
import com.example.githubapp.domain.search.models.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

data class SearchScreenState(
    val searchText: String = "",
    val repositories: Flow<PagingData<Repository>> = flowOf(),
    val showRepositoryList: Boolean = false,
)
