package com.example.githubapp.feature.assignedIssues.navigation

import com.example.githubapp.core.navigation.NavigationDestination

object AssignedIssuesScreenRouter : NavigationDestination {

    private const val ASSIGNED_ISSUES_SCREEN_ROUTE = "assignedIssues"

    override fun route() = ASSIGNED_ISSUES_SCREEN_ROUTE
}
