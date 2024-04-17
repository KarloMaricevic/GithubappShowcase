package com.example.githubapp.feature.search.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.githubapp.R
import com.example.githubapp.designSystem.theme.GithubAppTheme
import com.example.githubapp.designSystem.theme.blue
import com.example.githubapp.designSystem.theme.gray500

@Composable
fun SearchBar(
    text: String,
    onTextChange: (String) -> Unit,
    onConfigureClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.height(IntrinsicSize.Min),
    ) {
        Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = stringResource(R.string.default_icon_content_description),
            modifier = Modifier.padding(horizontal = 16.dp),
        )
        BasicTextField(
            value = text,
            onValueChange = onTextChange,
            keyboardActions = KeyboardActions(onSearch = {
                focusManager.clearFocus()
                keyboardController?.hide()
            }),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search,
            ),
            singleLine = true,
            cursorBrush = SolidColor(blue),
            textStyle = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.onBackground),
            modifier = Modifier.weight(1f, true),
            decorationBox = { innerTextField ->
                Column(
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center,
                ) {
                    Box(Modifier.padding(vertical = 4.dp)) {
                        if (text.isEmpty()) {
                            Text(
                                text = stringResource(R.string.search_screen_search_bar_placeholder),
                                color = gray500,
                            )
                        }
                        innerTextField()
                    }
                    HorizontalDivider(Modifier.height(2.dp))
                }
            }
        )
        Icon(
            painter = painterResource(R.drawable.ic_settings),
            contentDescription = stringResource(R.string.default_icon_content_description),
            tint = blue,
            modifier = Modifier
                .padding(start = 16.dp, end = 8.dp)
                .clip(CircleShape)
                .clickable { onConfigureClicked() }
                .padding(8.dp)
                .size(24.dp),
        )
    }
}

@Preview
@Composable
fun SearchBarPreview() {
    GithubAppTheme {
        SearchBar(
            text = "Query",
            onTextChange = {},
            onConfigureClicked = {},
        )
    }
}

@Preview
@Composable
fun SearchBarHintPreview() {
    GithubAppTheme {
        SearchBar(
            text = "",
            onTextChange = {},
            onConfigureClicked = {},
        )
    }
}
