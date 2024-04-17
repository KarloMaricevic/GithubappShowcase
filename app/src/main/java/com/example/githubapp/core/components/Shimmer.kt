package com.example.githubapp.core.components

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun Shimmer(
    modifier: Modifier,
    color: Color = Color.Gray,
) {
    val infiniteTransition = rememberInfiniteTransition("")
    val placeHolderEndColor = color.copy(alpha = 0.2f)
    val animatedColor by infiniteTransition.animateColor(
        initialValue = color,
        targetValue = placeHolderEndColor,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse,
        ),
        label = "",
    )
    Box(modifier.background(animatedColor))
}
