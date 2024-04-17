package com.example.githubapp.feature.profile.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.githubapp.R
import com.example.githubapp.core.components.Image
import com.example.githubapp.designSystem.theme.gray500
import com.example.githubapp.domain.models.Profile

@Composable
fun ProfileHeadline(
    profile: Profile,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier
            .fillMaxWidth()
            .padding(
                top = 12.dp,
                bottom = 2.dp
            ),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                image = profile.avatarUrl,
                modifier = Modifier
                    .size(70.dp)
                    .clip(CircleShape),
            )
            Column(
                modifier = Modifier.padding(start = 12.dp),
                verticalArrangement = Arrangement.Center,
            ) {
                if (profile.name != null) {
                    Text(text = profile.name, Modifier.padding(bottom = 8.dp))
                }
                Text(text = profile.login, color = gray500)
            }
        }
        Text(text = profile.bio ?: "", modifier = Modifier.padding(top = 12.dp))
    }
}

@Preview
@Composable
fun ProfileHeadlinePreview() {
    ProfileHeadline(
        Profile(
            avatarUrl = com.example.githubapp.domain.models.Image.LocalImage(R.drawable.ic_launcher_foreground),
            login = "name",
            name = "KarloMaricevic",
            bio = "This is users bio is a long text",
            url = "",
        )
    )
}
