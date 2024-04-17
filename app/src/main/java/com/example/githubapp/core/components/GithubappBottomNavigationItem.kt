package com.example.githubapp.core.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.example.githubapp.R
import com.example.githubapp.core.navigation.BottomNavigationItem

@Composable
fun RowScope.GithubappBottomNavigationItem(
    screen: BottomNavigationItem,
    currentDestination: NavDestination?,
    navController: NavHostController,
) {
    GithubappNavigationItem(
        icon = {
            Icon(
                imageVector = screen.icon,
                contentDescription = stringResource(R.string.default_icon_content_description)
            )
        },
        label = { Text(stringResource(screen.label)) },
        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        },
    )
}
