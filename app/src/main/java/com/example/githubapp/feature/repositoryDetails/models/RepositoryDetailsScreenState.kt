package com.example.githubapp.feature.repositoryDetails.models

import com.example.githubapp.core.components.SimpleDialogUI
import com.example.githubapp.domain.search.models.Repository

data class RepositoryDetailsScreenState(
    val repository: Repository? = null,
    val isStarred: Boolean = false,
    val starLoading: Boolean = false,
    val isLoading: Boolean = true,
    val dialog: SimpleDialogUI? = null,
)
