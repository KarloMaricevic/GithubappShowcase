package com.example.githubapp.feature.search.viewmodel

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.githubapp.core.base.BaseViewModel
import com.example.githubapp.core.base.TIMEOUT_DELAY
import com.example.githubapp.domain.search.usecase.SearchForRepository
import com.example.githubapp.feature.search.model.SearchScreenEvent
import com.example.githubapp.feature.search.model.SearchScreenEvent.OnOpenFiltersClicked
import com.example.githubapp.feature.search.model.SearchScreenEvent.OnSearchTextChanged
import com.example.githubapp.feature.search.model.SearchScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchForRepository: SearchForRepository,
) : BaseViewModel<SearchScreenEvent>() {

    private companion object {
        const val QUERY_DEBOUNCE = 500L
        const val MINIMAL_QUERY_LENGTH_TO_START_SEARCH = 3
    }

    private val searchText = MutableStateFlow("")

    private val showRepositoryList = searchText
        .mapLatest { text -> text.length >= MINIMAL_QUERY_LENGTH_TO_START_SEARCH }

    private val repositories = searchText
        .debounce(QUERY_DEBOUNCE)
        .mapLatest { query ->
            if (query.length >= MINIMAL_QUERY_LENGTH_TO_START_SEARCH) {
                searchForRepository(query).cachedIn(viewModelScope)
            } else {
                flowOf(PagingData.empty())
            }
        }

    val state = combine(
        searchText,
        repositories,
        showRepositoryList,
        ::SearchScreenState
    ).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(TIMEOUT_DELAY),
        initialValue = SearchScreenState(),
    )

    override fun onEvent(event: SearchScreenEvent) {
        when (event) {
            is OnSearchTextChanged -> searchText.update { event.text }
            is OnOpenFiltersClicked -> {}
        }
    }
}
