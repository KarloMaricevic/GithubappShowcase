package com.example.githubapp.domain.search.models

import com.example.githubapp.domain.models.AuthorInfo

data class Repository(
    val id: Long,
    val name: String,
    val author: AuthorInfo,
    val fullName: String,
    val description: String?,
    val language: String?,
    val staredTimes: Int,
    val url: String? = null,
    val forkedTimes: Int,
)
