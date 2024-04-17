package com.example.githubapp.feature.profile.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.githubapp.R
import com.example.githubapp.core.components.Shimmer
import com.example.githubapp.domain.models.Image
import com.example.githubapp.domain.models.Profile

@Composable
fun ProfileHeadlineLoading(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier
            .fillMaxWidth()
            .padding(
                top = 12.dp,
                bottom = 2.dp
            ),
    )
    {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Shimmer(
                modifier = Modifier
                    .size(70.dp)
                    .clip(CircleShape),
            )
            Column(
                modifier = Modifier.padding(start = 12.dp),
                verticalArrangement = Arrangement.Center,
            ) {
                Shimmer(
                    Modifier
                        .padding(bottom = 8.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .size(width = 200.dp, height = 18.dp),
                )
                Shimmer(
                    Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .size(width = 150.dp, height = 18.dp),
                )
            }
        }
        Shimmer(
            Modifier
                .padding(top = 12.dp)
                .clip(RoundedCornerShape(12.dp))
                .size(width = 250.dp, height = 52.dp),
        )
    }
}

@Preview
@Composable
fun ProfileHeadlineLoadingPreview() {
    ProfileHeadline(
        Profile(
            avatarUrl = Image.LocalImage(R.drawable.ic_launcher_foreground),
            login = "name",
            name = "KarloMaricevic",
            bio = "This is users bio is a long text",
            url = "",
        )
    )
}
