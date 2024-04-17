package com.example.githubapp.domain.profile

import com.example.githubapp.data.profile.ProfileDatasource
import com.example.githubapp.domain.helpers.toResult
import com.example.githubapp.domain.profile.mappers.ProfileMapper
import javax.inject.Inject

class ProfileRepository @Inject constructor(
    private val datasource: ProfileDatasource,
    private val profileMapper: ProfileMapper,
) {

    suspend fun getAuthenticatedUserProfile() =
        datasource.getAuthUserProfile().toResult { content ->
            profileMapper.map(content)
        }
}
