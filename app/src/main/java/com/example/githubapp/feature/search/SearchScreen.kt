package com.example.githubapp.feature.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.githubapp.R
import com.example.githubapp.core.components.SimpleLazyColumn
import com.example.githubapp.feature.search.components.RepositoryItem
import com.example.githubapp.feature.search.components.SearchBar
import com.example.githubapp.feature.search.model.SearchScreenEvent.OnOpenFiltersClicked
import com.example.githubapp.feature.search.model.SearchScreenEvent.OnSearchTextChanged
import com.example.githubapp.feature.search.viewmodel.SearchViewModel

@Composable
fun SearchScreen(
    innerPadding: PaddingValues,
    viewModel: SearchViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()
    val repositories = state.repositories.collectAsLazyPagingItems()

    Column(
        Modifier
            .padding(innerPadding)
            .fillMaxSize()
    ) {
        SearchBar(
            text = state.searchText,
            onTextChange = { text -> viewModel.onEvent(OnSearchTextChanged(text)) },
            onConfigureClicked = { viewModel.onEvent(OnOpenFiltersClicked) },
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surface)
                .padding(top = 8.dp)
                .padding(bottom = 4.dp)
        )
        if (state.showRepositoryList) {
            SimpleLazyColumn(
                items = repositories,
                key = { id },
                uiItemBuilder = { repository -> RepositoryItem(repository) },
                noItemsItem = { NoItems() },
                modifier = Modifier.weight(1f, true),
                itemSpacing = 2.dp,
                topInset = 8.dp,
            )
        } else {
            Box(Modifier.fillMaxSize()) {
                Text(
                    text = stringResource(R.string.search_screen_start_search_hint),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}

@Composable
fun NoItems(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier
            .fillMaxSize()
            .padding(horizontal = 64.dp)
    ) {
        Text(
            text = stringResource(R.string.search_screen_no_repositories_matching_your_query),
            modifier = Modifier.align(Alignment.Center),
            textAlign = TextAlign.Center
        )
    }
}