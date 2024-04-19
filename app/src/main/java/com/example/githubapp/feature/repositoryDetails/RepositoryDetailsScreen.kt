package com.example.githubapp.feature.repositoryDetails

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.githubapp.R
import com.example.githubapp.core.components.SimpleDialog
import com.example.githubapp.feature.repositoryDetails.components.RepositoryDetailsHeadline
import com.example.githubapp.feature.repositoryDetails.models.RepositoryDetailsScreenEvent.OnBackClicked
import com.example.githubapp.feature.repositoryDetails.models.RepositoryDetailsVMParam
import com.example.githubapp.feature.repositoryDetails.viewmodel.RepositoryDetailsViewModel
import com.example.githubapp.feature.repositoryDetails.viewmodel.RepositoryDetailsViewModel.RepositoryDetailsViewModelFactory

@Composable
fun RepositoryDetailsScreen(
    owner: String,
    repoName: String,
) {
    val viewModel =
        hiltViewModel<RepositoryDetailsViewModel, RepositoryDetailsViewModelFactory> { factory ->
            factory.create(RepositoryDetailsVMParam(owner, repoName))
        }
    val viewState by viewModel.viewState.collectAsState()
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .padding(top = 8.dp)
            .fillMaxSize(),
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_arrow_left),
            contentDescription = stringResource(R.string.default_icon_content_description),
            modifier = Modifier
                .padding(4.dp)
                .clip(CircleShape)
                .clickable { viewModel.onEvent(OnBackClicked) }
                .padding(4.dp)
                .size(32.dp)
        )
        viewState.repository?.let { notNullRepository ->
            RepositoryDetailsHeadline(
                repository = notNullRepository,
                isStarred = viewState.isStarred,
                showLoaderInsteadOfStar = viewState.starLoading,
                interactionHandler = viewModel::onEvent,
            )
        }
        if (viewState.isLoading) {
            Box(Modifier.fillMaxSize()) {
                CircularProgressIndicator(Modifier.align(Alignment.Center))
            }
        }
    }
    viewState.dialog?.let { notNullDialog -> SimpleDialog(notNullDialog) }
}
