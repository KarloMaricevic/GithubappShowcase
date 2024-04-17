package com.example.githubapp.core.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.githubapp.designSystem.theme.GithubAppTheme
import com.example.githubapp.designSystem.theme.blue
import com.example.githubapp.designSystem.theme.gray500

@Composable
fun GithubappNavigationMenu(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit,
) {
    NavigationBar(
        modifier = modifier,
        content = content,
        tonalElevation = 0.dp,
    )
}

@Composable
fun RowScope.GithubappNavigationItem(
    selected: Boolean,
    onClick: () -> Unit,
    label: @Composable (() -> Unit)? = null,
    icon: @Composable () -> Unit,
) {
    NavigationBarItem(
        selected = selected,
        onClick = onClick,
        alwaysShowLabel = false,
        label = label,
        icon = icon,
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = blue,
            unselectedIconColor = gray500,
            selectedTextColor = blue,
            unselectedTextColor = gray500,
        )
    )
}

@Preview
@Composable
fun GithubappBottomAppBarPreview() {
    GithubappNavigationMenu {
        repeat(4) {
            GithubappNavigationItem(
                selected = it == 2,
                onClick = {},
                label = { Text(text = "Label") },
                icon = { Icon(Icons.Default.Favorite, contentDescription = "") },
            )
        }
    }
}

@Composable
fun GithubappNavigationItemPreview() {
    GithubAppTheme {
        Row {
            GithubappNavigationItem(
                selected = true,
                onClick = {},
                label = { Text(text = "Favorite") },
                icon = { Icon(Icons.Default.Favorite, contentDescription = "") },
            )
            GithubappNavigationItem(
                selected = false,
                onClick = {},
                icon = { Icon(Icons.Default.Delete, contentDescription = "") },
                label = { Text(text = "Delete") },
            )
        }
    }
}
