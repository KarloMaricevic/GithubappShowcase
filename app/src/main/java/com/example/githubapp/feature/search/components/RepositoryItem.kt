package com.example.githubapp.feature.search.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
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
import com.example.githubapp.designSystem.theme.GithubAppTheme
import com.example.githubapp.designSystem.theme.gray500
import com.example.githubapp.designSystem.theme.yellow
import com.example.githubapp.domain.models.AuthorInfo
import com.example.githubapp.domain.search.models.Repository

@Composable
fun RepositoryItem(
    repository: Repository,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier
            .background(MaterialTheme.colorScheme.surface)
            .padding(8.dp)
            .fillMaxWidth(),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
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
                )
            )
        }
        Column(Modifier.padding(top = 8.dp)) {
            Text(
                text = repository.name,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                modifier = Modifier.padding(end = 24.dp),
                style = MaterialTheme.typography.titleMedium,
            )
            if (!repository.description.isNullOrEmpty()) {
                Text(
                    text = repository.description,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 3,
                    modifier = Modifier.padding(top = 4.dp),
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 16.dp),
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_star_filled),
                    contentDescription = stringResource(R.string.default_icon_content_description),
                    tint = yellow,
                    modifier = Modifier.padding(end = 4.dp),
                )
                Text(text = repository.staredTimes.toString())
                if (!repository.language.isNullOrEmpty()) {
                    Text(
                        text = repository.language,
                        color = gray500,
                        modifier = Modifier.padding(start = 8.dp),
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun RepositoryItemPreview() {
    GithubAppTheme {
        RepositoryItem(
            Repository(
                id = 1,
                name = "RepoName",
                author = AuthorInfo(
                    username = "Karlo",
                    avatar = com.example.githubapp.domain.models.Image.LocalImage(R.drawable.ic_launcher_foreground),
                ),
                fullName = "Karlo/Kotlin",
                description = "Description",
                language = "Kotlin",
                staredTimes = 1900,
            )
        )
    }
}

@Preview
@Composable
fun RepositoryItemLongTextPreview() {
    GithubAppTheme {
        RepositoryItem(
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
            )
        )
    }
}
