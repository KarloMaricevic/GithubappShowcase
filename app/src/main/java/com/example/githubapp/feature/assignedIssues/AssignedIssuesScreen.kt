package com.example.githubapp.feature.assignedIssues

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.githubapp.R
import com.example.githubapp.core.components.SimpleLazyColumn
import com.example.githubapp.feature.assignedIssues.components.IssueItem
import com.example.githubapp.feature.assignedIssues.models.AssignedIssuesEvent.OnBackClicked
import com.example.githubapp.feature.assignedIssues.models.AssignedIssuesEvent.OnIssueClicked

@Composable
fun AssignedIssuesScreen(
    viewModel: AssignedIssuesViewModel = hiltViewModel()
) {

    val issues = viewModel.issues.collectAsLazyPagingItems()

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_arrow_left),
                contentDescription = stringResource(R.string.default_icon_content_description),
                modifier = Modifier
                    .padding(4.dp)
                    .clip(CircleShape)
                    .clickable { viewModel.onEvent(OnBackClicked) }
                    .padding(4.dp)
                    .size(30.dp),
            )
            Text(
                text = stringResource(R.string.issues),
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(start = 16.dp),
            )
        }
        SimpleLazyColumn(
            items = issues,
            key = { id },
            uiItemBuilder = { issue ->
                IssueItem(
                    issue = issue,
                    modifier = Modifier.clickable {
                        viewModel.onEvent(OnIssueClicked(issue.id))
                    },
                )
            },
            noItemsItem = { Text(text = stringResource(R.string.no_items)) }
        )
    }
}