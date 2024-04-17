package com.example.githubapp.data.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class IssueResponse(
    @JsonProperty("id") val id: Long,
    @JsonProperty("repository_url") val repositoryUrl: String,
    @JsonProperty("title") val title: String,
    @JsonProperty("body") val body: String,
    @JsonProperty("user") val author: AuthorInfoResponse,
    @JsonProperty("pull_request") val pullRequestLinksResponse: PullRequestLinksResponse?,
    @JsonProperty("state") val state: String,
    @JsonProperty("repository") val repository: RepositoryResponse
)
