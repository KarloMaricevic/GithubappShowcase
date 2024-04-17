package com.example.githubapp.data.credencials

import com.example.githubapp.data.ApiConstants
import com.example.githubapp.data.models.AccessTokenResponse
import retrofit2.http.POST
import retrofit2.http.Query

interface CredentialsApi {

    @POST(".")
    suspend fun getAccessToken(
        @Query("code") code: String,
        @Query("client_id") clientId: String = ApiConstants.CLIENT_ID,
        @Query("client_secret") clientSecret: String = ApiConstants.CLIENT_SECRET,
    ): AccessTokenResponse
}
