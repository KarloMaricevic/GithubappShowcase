package com.example.githubapp.feature.assignedIssues

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.githubapp.core.base.BaseViewModel
import com.example.githubapp.core.navigation.NavigationEvent
import com.example.githubapp.core.navigation.Navigator
import com.example.githubapp.domain.issues.usecase.GetAuthenticatedUserIssues
import com.example.githubapp.feature.assignedIssues.models.AssignedIssuesEvent
import com.example.githubapp.feature.assignedIssues.models.AssignedIssuesEvent.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AssignedIssuesViewModel @Inject constructor(
    getAuthenticatedUserIssues: GetAuthenticatedUserIssues,
    private val navigator: Navigator,
) : BaseViewModel<AssignedIssuesEvent>() {


    val issues = getAuthenticatedUserIssues()
        .cachedIn(viewModelScope)

    override fun onEvent(event: AssignedIssuesEvent) {
        when (event) {
            is OnBackClicked -> viewModelScope.launch {
                navigator.emitDestination(NavigationEvent.NavigateUp)
            }

            else -> {

            }
        }
    }
}