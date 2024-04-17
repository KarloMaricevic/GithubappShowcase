package com.example.githubapp.feature.home

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.githubapp.core.components.GithubappBottomNavigationItem
import com.example.githubapp.core.components.GithubappNavigationMenu
import com.example.githubapp.core.navigation.BottomNavigationItem
import com.example.githubapp.feature.overview.OverviewScreen
import com.example.githubapp.feature.overview.navigation.OverviewScreenRouter
import com.example.githubapp.feature.overview.viewmodel.OverviewViewModel
import com.example.githubapp.feature.profile.ProfileScreen
import com.example.githubapp.feature.profile.navigation.ProfileScreenRouter
import com.example.githubapp.feature.profile.viewmodel.ProfileViewModel
import com.example.githubapp.feature.search.SearchScreen
import com.example.githubapp.feature.search.navigation.SearchScreenRouter
import com.example.githubapp.feature.search.viewmodel.SearchViewModel

@Composable
fun HomeScreen() {
    val navController = rememberNavController()

    val overviewViewModel: OverviewViewModel = hiltViewModel()
    val searchViewModel: SearchViewModel = hiltViewModel()
    val profileViewModel: ProfileViewModel = hiltViewModel()

    Scaffold(
        bottomBar = {
            GithubappNavigationMenu {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                BottomNavigationItem.HOME_NAVIGATION_ITEMS.forEach { screen ->
                    GithubappBottomNavigationItem(
                        screen,
                        currentDestination,
                        navController,
                    )
                }
            }
        },
        content = { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = OverviewScreenRouter.route(),
            ) {
                composable(OverviewScreenRouter.route()) {
                    OverviewScreen(
                        viewModel = overviewViewModel,
                        paddingValues = innerPadding,
                    )
                }
                composable(SearchScreenRouter.route()) {
                    SearchScreen(
                        innerPadding = innerPadding,
                        viewModel = searchViewModel,
                    )
                }
                composable(ProfileScreenRouter.route()) {
                    ProfileScreen(
                        paddingValues = innerPadding,
                        viewModel = profileViewModel,
                    )
                }
            }
        },
    )
}
