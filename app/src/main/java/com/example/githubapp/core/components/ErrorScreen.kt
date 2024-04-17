package com.example.githubapp.core.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.githubapp.R
import com.example.githubapp.designSystem.theme.GithubAppTheme
import com.example.githubapp.designSystem.theme.white

@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier,
    onRetryClicked: () -> Unit,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = stringResource(R.string.unexpected_error_description),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .padding(bottom = 20.dp),
        )
        Button(onClick = { onRetryClicked() }) {
            Text(
                text = stringResource(R.string.try_again),
                color = white,
            )
        }
    }
}

@Preview
@Composable
fun ErrorScreenPreview() {
    GithubAppTheme {
        ErrorScreen {}
    }
}
