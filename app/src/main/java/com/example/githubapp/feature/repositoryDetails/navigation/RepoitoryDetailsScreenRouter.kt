package com.example.githubapp.feature.repositoryDetails.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.githubapp.core.navigation.NavigationDestination

object RepositoryDetailsScreenRouter : NavigationDestination {

    private const val REPOSITORY_DETAILS_SCREEN_ROOT = "repositoryDetails"
    const val OWNER_PARAM = "owner"
    const val REPOSITORY_NAME_PARAM = "repository"

    private const val DETAILS_SCREEN_ROUTE =
        "$REPOSITORY_DETAILS_SCREEN_ROOT/{$OWNER_PARAM}/{$REPOSITORY_NAME_PARAM}"

    override fun route() = DETAILS_SCREEN_ROUTE

    override val arguments: List<NamedNavArgument>
        get() = listOf(
            navArgument(OWNER_PARAM) { type = NavType.StringType },
            navArgument(REPOSITORY_NAME_PARAM) { type = NavType.StringType }
        )

    fun creteRepositoryDetailsRoute(owner: String, repositoryName: String) =
        "$REPOSITORY_DETAILS_SCREEN_ROOT/$owner/$repositoryName"
}
