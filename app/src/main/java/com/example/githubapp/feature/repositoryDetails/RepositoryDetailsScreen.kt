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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.githubapp.R
import com.example.githubapp.core.components.SimpleDialog
import com.example.githubapp.domain.models.AuthorInfo
import com.example.githubapp.domain.models.Image
import com.example.githubapp.domain.search.models.Repository
import com.example.githubapp.feature.repositoryDetails.components.RepositoryDetailsHeadline
import com.example.githubapp.feature.repositoryDetails.models.RepositoryDetailsScreenState

@Composable
fun RepositoryDetailsScreen(
    owner: String,
    repoName: String,
) {
    val viewState = RepositoryDetailsScreenState(
        repository = Repository(
            id = 1,
            name = "RepoName",
            author = AuthorInfo(
                username = "Karlo",
                avatar = Image.LocalImage(R.drawable.ic_launcher_foreground),
            ),
            fullName = "Karlo/Kotlin",
            description = "Description",
            language = "",
            staredTimes = 1900,
            forkedTimes = 150,
            url = "https://github.com/KarloMaricevic/GithubappShowcase",
        ),
    )
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
                .clickable { }
                .padding(4.dp)
                .size(32.dp)
        )
        viewState.repository?.let { notNullRepository ->
            RepositoryDetailsHeadline(
                repository = notNullRepository,
                isStarred = viewState.isStarred,
                interactionHandler = {},
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
