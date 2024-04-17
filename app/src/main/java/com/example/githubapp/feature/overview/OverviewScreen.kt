package com.example.githubapp.feature.overview

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.githubapp.R
import com.example.githubapp.core.components.GithubAppLabel
import com.example.githubapp.designSystem.theme.GithubAppTheme
import com.example.githubapp.designSystem.theme.Typography
import com.example.githubapp.designSystem.theme.green300
import com.example.githubapp.designSystem.theme.lightBlue
import com.example.githubapp.feature.overview.models.OverviewScreenEvents
import com.example.githubapp.feature.overview.viewmodel.OverviewViewModel

@Composable
fun OverviewScreen(
    viewModel: OverviewViewModel = hiltViewModel(),
    paddingValues: PaddingValues,
) {
    Column(
        Modifier
            .padding(paddingValues)
            .background(MaterialTheme.colorScheme.surface)
            .padding(bottom = 16.dp)
            .fillMaxWidth(),
    ) {
        Text(
            text = stringResource(R.string.overview_screen_my_work_title),
            modifier = Modifier.padding(bottom = 16.dp, start = 12.dp, top = 10.dp),
            style = Typography.titleLarge,
        )
        GithubAppLabel(
            label = stringResource(R.string.issues),
            indicator = R.drawable.ic_issues,
            indicatorBackground = green300,
            modifier = Modifier.clickable {
                viewModel.onEvent(OverviewScreenEvents.OnIssuesClicked)
            }
        )
        GithubAppLabel(
            label = stringResource(R.string.pull_requests),
            indicator = R.drawable.ic_pull_requests,
            indicatorBackground = lightBlue,
            modifier = Modifier.clickable {
                viewModel.onEvent(OverviewScreenEvents.OnIssuesClicked)
            }
        )
    }
}

@Preview
@Composable
fun ProfileScreenPreview() {
    GithubAppTheme {
        OverviewScreen(paddingValues = PaddingValues())
    }
}
