package com.example.githubapp.core.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.githubapp.R
import com.example.githubapp.domain.models.Image

@Composable
fun Image(
    image: Image,
    modifier: Modifier = Modifier,
    contentDescription: String = stringResource(R.string.default_image_content_description),
    alignment: Alignment = Alignment.Center,
    contentScale: ContentScale = ContentScale.Fit,
) {
    when (image) {
        is Image.RemoteImage -> {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(image.url)
                    .crossfade(true)
                    .build(),
                placeholder = ColorPainter(MaterialTheme.colorScheme.background),
                contentDescription = contentDescription,
                contentScale = ContentScale.Crop,
                modifier = modifier,
            )
        }

        is Image.LocalImage -> androidx.compose.foundation.Image(
            painter = painterResource(image.id),
            contentDescription = stringResource(R.string.default_image_content_description),
            modifier = modifier,
            alignment = alignment,
            contentScale = contentScale,
        )
    }
}
