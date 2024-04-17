package com.example.githubapp.core.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.githubapp.R
import com.example.githubapp.designSystem.theme.white

@Composable
fun GithubAppLabel(
    label: String,
    indicatorBackground: Color,
    @DrawableRes indicator: Int,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.padding(
            vertical = 12.dp,
            horizontal = 12.dp,
        ),
    ) {
        Icon(
            painter = painterResource(indicator),
            contentDescription = stringResource(R.string.default_icon_content_description),
            modifier = Modifier
                .clip(RoundedCornerShape(4.dp))
                .background(indicatorBackground)
                .size(28.dp)
                .padding(6.dp),
            tint = white,
        )
        Text(
            text = label,
            modifier = Modifier
                .padding(start = 8.dp)
                .weight(weight = 1f, fill = true)
                .wrapContentHeight(),
        )
    }
}

@Preview
@Composable
fun PulRequestLabelPreview() {
    GithubAppLabel(
        label = "Issues",
        indicatorBackground = Color.Green,
        indicator = R.drawable.ic_issues,
    )
}
