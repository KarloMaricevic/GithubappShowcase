package com.example.githubapp.feature.assignedIssues.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.githubapp.R
import com.example.githubapp.designSystem.theme.GithubAppTheme
import com.example.githubapp.designSystem.theme.green500
import com.example.githubapp.designSystem.theme.purple
import com.example.githubapp.domain.models.AuthorInfo
import com.example.githubapp.domain.models.Image
import com.example.githubapp.domain.models.Issue
import com.example.githubapp.domain.models.Issue.State.CLOSED
import com.example.githubapp.domain.models.Issue.State.OPEN
import com.example.githubapp.domain.search.models.Repository

@Composable
fun IssueItem(issue: Issue, modifier: Modifier = Modifier) {
    Row(
        modifier
            .background(MaterialTheme.colorScheme.surface)
            .fillMaxWidth()
            .padding(8.dp),
    ) {
        Icon(
            painter = painterResource(
                when (issue.status) {
                    OPEN -> R.drawable.ic_issues
                    CLOSED -> R.drawable.ic_issue_closed
                },
            ),
            contentDescription = stringResource(R.string.default_icon_content_description),
            tint = when (issue.status) {
                OPEN -> green500
                CLOSED -> purple
            },
            modifier = Modifier.size(24.dp),
        )
        Column(Modifier.padding(horizontal = 8.dp)) {
            Text(
                text = issue.repository.fullName,
                style = MaterialTheme.typography.bodyMedium,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
            )
            Text(
                text = issue.title,
                style = MaterialTheme.typography.titleMedium,
                overflow = TextOverflow.Ellipsis,
                maxLines = 3,
            )
        }
    }
}

@Preview
@Composable
fun OpenedIssuePreview() {
    GithubAppTheme {
        IssueItem(
            issue = Issue(
                id = 0,
                repoName = "",
                repository = Repository(
                    0,
                    "",
                    author = AuthorInfo("", Image.LocalImage(R.drawable.ic_launcher_foreground)),
                    "karlomaricevic/shortName",
                    description = "description",
                    language = "Kotlin",
                    staredTimes = 1900
                ),
                authorInfo = AuthorInfo("", Image.LocalImage(R.drawable.ic_launcher_foreground)),
                description = "",
                status = OPEN,
                title = "ShorTitle",
            )
        )
    }
}

@Preview
@Composable
fun ClosedIssuePreview() {
    GithubAppTheme {
        IssueItem(
            issue = Issue(
                id = 0,
                repoName = "",
                repository = Repository(
                    0,
                    "",
                    author = AuthorInfo("", Image.LocalImage(R.drawable.ic_launcher_foreground)),
                    "karlomaricevic/" + List(20) { "shorName" }.joinToString(),
                    description = "description",
                    language = "Kotlin",
                    staredTimes = 1900
                ),
                authorInfo = AuthorInfo("", Image.LocalImage(R.drawable.ic_launcher_foreground)),
                description = "",
                status = CLOSED,
                title = List(20) { "LongTitle" }.joinToString(),
            )
        )
    }
}
