package com.example.githubapp.feature.repositoryDetails

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun RepositoryDetailsScreen(
    owner: String,
    repoName: String,
) {
    Box(Modifier.fillMaxSize()) {
        Text(
            text = "Repo details screen",
            modifier = Modifier.align(Alignment.Center),
        )
    }
}