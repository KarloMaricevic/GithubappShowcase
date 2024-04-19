package com.example.githubapp.feature.repositoryDetails.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.githubapp.R
import com.example.githubapp.core.components.Image
import com.example.githubapp.designSystem.theme.gray500
import com.example.githubapp.domain.models.AuthorInfo
import com.example.githubapp.domain.search.models.Repository
import com.example.githubapp.feature.repositoryDetails.models.RepositoryDetailsScreenEvent
import com.example.githubapp.feature.repositoryDetails.models.RepositoryDetailsScreenEvent.OnLinkClicked

@Composable
fun RepositoryDetailsHeadline(
    repository: Repository,
    isStarred: Boolean,
    interactionHandler: (RepositoryDetailsScreenEvent) -> Unit,
) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                image = repository.author.avatar,
                contentDescription = stringResource(R.string.default_image_content_description),
                modifier = Modifier
                    .clip(CircleShape)
                    .size(24.dp),
            )
            Text(
                text = repository.author.username,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                color = gray500,
                modifier = Modifier.padding(
                    start = 4.dp,
                    end = 80.dp,
                ),
            )
        }
        Text(
            text = repository.name,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            modifier = Modifier.padding(end = 24.dp, top = 8.dp),
            style = MaterialTheme.typography.headlineLarge,
        )
        if (!repository.description.isNullOrEmpty()) {
            Text(
                text = repository.description,
                modifier = Modifier.padding(top = 4.dp),
                style = MaterialTheme.typography.bodyLarge.copy(color = gray500)
            )
        }
        if (!repository.url.isNullOrEmpty()) {
            Url(
                url = repository.url,
                onUrlClicked = { interactionHandler(OnLinkClicked(repository.url)) },
            )
        }
        StaredAndForkedCount(
            starredTimes = repository.staredTimes,
            forkedTimes = repository.forkedTimes,
            modifier = Modifier.padding(top = 16.dp),
        )
        StarButton(isStarred = isStarred, modifier = Modifier.padding(top = 12.dp))
    }
}

@Composable
private fun Url(
    url: String,
    onUrlClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .padding(4.dp)
            .clickable(onClick = onUrlClicked),
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_url),
            contentDescription = stringResource(R.string.default_image_content_description),
            tint = gray500,
        )
        Text(
            text = url,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            modifier = Modifier.padding(end = 24.dp, start = 6.dp),
            style = MaterialTheme.typography.titleMedium,
        )
    }
}

@Composable
fun StaredAndForkedCount(
    starredTimes: Int,
    forkedTimes: Int,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier,
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_star_outlined),
            contentDescription = stringResource(R.string.default_icon_content_description),
            tint = gray500,
        )
        Text(
            text = starredTimes.toString(),
            Modifier.padding(horizontal = 4.dp),
        )
        Text(
            text = stringResource(R.string.repository_details_screen_star),
            color = gray500,
        )
        Icon(
            painter = painterResource(R.drawable.ic_fork),
            contentDescription = stringResource(R.string.default_icon_content_description),
            tint = gray500,
            modifier = Modifier.padding(end = 4.dp, start = 8.dp),
        )
        Text(text = forkedTimes.toString())
        Text(
            text = stringResource(R.string.repository_details_screen_fork),
            Modifier.padding(horizontal = 4.dp),
            color = gray500,
        )
    }
}

@Composable
fun StarButton(
    isStarred: Boolean,
    modifier: Modifier = Modifier,
) {
    Button(
        modifier = modifier.fillMaxWidth(),
        onClick = {},
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.surface),
        shape = RoundedCornerShape(4.dp),
    ) {
        Icon(
            painter = painterResource(
                if (isStarred) {
                    R.drawable.ic_star_filled
                } else {
                    R.drawable.ic_star_outlined
                }
            ),
            tint = gray500,
            modifier = Modifier.padding(end = 6.dp),
            contentDescription = stringResource(R.string.default_icon_content_description),
        )
        Text(
            text = stringResource(R.string.repository_details_screen_star_button),
            color = gray500,
        )
    }
}

@Preview
@Composable
private fun RepositoryDetailsHeadlinePreview() {
    RepositoryDetailsHeadline(
        Repository(
            id = 1,
            name = "RepoName",
            author = AuthorInfo(
                username = "Karlo",
                avatar = com.example.githubapp.domain.models.Image.LocalImage(R.drawable.ic_launcher_foreground),
            ),
            fullName = "Karlo/Kotlin",
            description = "Description",
            language = "",
            staredTimes = 1900,
            forkedTimes = 150,
            url = "https://github.com/KarloMaricevic/GithubappShowcase",
        ),
        isStarred = false,
        interactionHandler = {},
    )
}

@Preview
@Composable
private fun RepositoryDetailsHeadlineLongPreview() {
    RepositoryDetailsHeadline(
        Repository(
            id = 1,
            name = List(10) { "LongRepoName" }.joinToString(""),
            author = AuthorInfo(
                username = List(10) { "LongUsername" }.joinToString(""),
                avatar = com.example.githubapp.domain.models.Image.LocalImage(R.drawable.ic_launcher_foreground),
            ),
            fullName = "Karlo/Kotlin",
            description = List(30) { "LongDescription" }.joinToString(""),
            language = "Kotlin",
            staredTimes = 1900,
            forkedTimes = 150,
            url = "https://github.com/KarloMaricevic/GithubappShowcaseGithubappShowcaseGithubappShowcaseGithubappShowcase",
        ),
        isStarred = true,
        interactionHandler = {},
    )
}
