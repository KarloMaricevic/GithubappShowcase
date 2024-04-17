package com.example.githubapp.core.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.githubapp.R
import com.example.githubapp.feature.overview.navigation.OverviewScreenRouter
import com.example.githubapp.feature.profile.navigation.ProfileScreenRouter
import com.example.githubapp.feature.search.navigation.SearchScreenRouter

sealed class BottomNavigationItem(
    @StringRes val label: Int,
    val icon: ImageVector,
    val route: String
) {

    private data object Home : BottomNavigationItem(
        R.string.home_screen_first_bottom_tab_label,
        Icons.Filled.Home,
        OverviewScreenRouter.route(),
    )

    private data object Search : BottomNavigationItem(
        R.string.home_screen_search_bottom_tab_label,
        Icons.Filled.Search,
        SearchScreenRouter.route(),
    )

    private data object Profile : BottomNavigationItem(
        R.string.home_screen_profile_bottom_tab_label,
        Icons.Filled.Person,
        ProfileScreenRouter.route(),
    )

    companion object {
        val HOME_NAVIGATION_ITEMS = listOf(
            Home,
            Search,
            Profile,
        )
    }
}
