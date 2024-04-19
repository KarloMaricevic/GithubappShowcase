package com.example.githubapp.data.repository

import com.example.githubapp.data.models.RepositoryResponse
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface RepositoryApi {

    @GET("/repos/{owner}/{repo}")
    suspend fun getDetails(
        @Path("owner") owner: String,
        @Path("repo") repoName: String,
    ): RepositoryResponse

    @GET("/user/starred/{owner}/{repo}")
    suspend fun isStarred(
        @Path("owner") owner: String,
        @Path("repo") repoName: String,
    ): Response<Void>

    @PUT("/user/starred/{owner}/{repo}")
    suspend fun star(
        @Path("owner") owner: String,
        @Path("repo") repoName: String,
    ): Response<Void>

    @DELETE("/user/starred/{owner}/{repo}")
    suspend fun unStar(
        @Path("owner") owner: String,
        @Path("repo") repoName: String,
    ): Response<Void>
}
