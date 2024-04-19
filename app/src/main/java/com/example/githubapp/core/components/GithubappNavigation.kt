package com.example.githubapp.core.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.githubapp.core.navigation.NavigationEvent.Destination
import com.example.githubapp.core.navigation.NavigationEvent.NavigateBack
import com.example.githubapp.core.navigation.NavigationEvent.NavigateUp
import com.example.githubapp.core.navigation.Navigator
import com.example.githubapp.feature.assignedIssues.AssignedIssuesScreen
import com.example.githubapp.feature.assignedIssues.navigation.AssignedIssuesScreenRouter
import com.example.githubapp.feature.home.HomeScreen
import com.example.githubapp.feature.home.navigation.HomeScreenRouter
import com.example.githubapp.feature.login.LoginScreen
import com.example.githubapp.feature.login.navigation.LoginScreenRouter
import com.example.githubapp.feature.repositoryDetails.RepositoryDetailsScreen
import com.example.githubapp.feature.repositoryDetails.navigation.RepositoryDetailsScreenRouter

@Composable
fun GithubappNavigation(
    navigator: Navigator,
    navController: NavHostController = rememberNavController(),
    startScreen: Destination,
) {
    LaunchedEffect(key1 = Unit) {
        navigator.navigationEvent.collect { navigationEvent ->
            when (navigationEvent) {
                NavigateUp -> navController.navigateUp()
                NavigateBack -> navController.popBackStack()
                is Destination -> navController.navigate(
                    route = navigationEvent.destination,
                    builder = navigationEvent.builder,
                )
            }
        }
    }
    Scaffold { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = startScreen.destination,
            modifier = Modifier.padding(innerPadding),
        ) {
            composable(HomeScreenRouter.route()) { HomeScreen() }
            composable(LoginScreenRouter.route()) { LoginScreen() }
            composable(AssignedIssuesScreenRouter.route()) { AssignedIssuesScreen() }
            composable(RepositoryDetailsScreenRouter.route()) { entry ->
                val owner = entry.arguments?.getString(RepositoryDetailsScreenRouter.OWNER_PARAM)
                    ?: error("${RepositoryDetailsScreenRouter.OWNER_PARAM} was not provided to repository repositoryDetails screen")
                val repositoryName =
                    entry.arguments?.getString(RepositoryDetailsScreenRouter.REPOSITORY_NAME_PARAM)
                        ?: error("${RepositoryDetailsScreenRouter.REPOSITORY_NAME_PARAM} was not provided to repository repositoryDetails screen")
                RepositoryDetailsScreen(owner = owner, repoName = repositoryName)
            }
        }
    }
}
