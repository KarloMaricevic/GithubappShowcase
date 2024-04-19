package com.example.githubapp.data.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class RepositoryResponse(
    @JsonProperty("id") val id: Long,
    @JsonProperty("name") val name: String,
    @JsonProperty("homepage") val url: String?,
    @JsonProperty("owner") val owner: AuthorInfoResponse,
    @JsonProperty("full_name") val fullName: String,
    @JsonProperty("description") val description: String?,
    @JsonProperty("language") val language: String?,
    @JsonProperty("stargazers_count") val stargazersCount: Int,
    @JsonProperty("forks_count") val forksCount: Int
)
