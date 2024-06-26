package com.example.githubapp.data.profile

import com.example.githubapp.data.models.ProfileResponse
import retrofit2.http.GET

interface ProfileApi {

    @GET("user")
    suspend fun getAuthenticatedUserProfile(): ProfileResponse
}
