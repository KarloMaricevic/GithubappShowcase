package com.example.githubapp.fixtures.profile

import com.example.githubapp.domain.models.Image
import com.example.githubapp.domain.models.Profile
import com.example.githubapp.domain.profile.usecase.GetAuthenticatedUsersProfile

fun buildProfile(
    login: String = PROFILE_LOGIN,
    avatarUrl: String = AVATAR_URL,
    name: String = PROFILE_NAME,
    bio: String = PROFILE_BIO,
    profileUrl: String = PROFILE_URL,
) = Profile(
    login = login,
    avatarUrl = Image.RemoteImage(avatarUrl),
    name = name,
    bio = bio,
    url = profileUrl,
)

class FakeSuccessGetAuthenticatedUserProfile(
    private val profileToGive: Profile = buildProfile(),
) : GetAuthenticatedUsersProfile {

    override suspend fun invoke(): Result<Profile> = Result.success(profileToGive)
}

class FakeFailureGetAuthenticatedUserProfile(
    private val throwable: Throwable = Throwable(),
) : GetAuthenticatedUsersProfile {
    override suspend fun invoke() = Result.failure<Profile>(throwable)
}

class FakeFactoryGetAuthenticatedUserProfile(
    private val profileToGive: Profile = buildProfile(),
    private val throwable: Throwable = Throwable()
) : GetAuthenticatedUsersProfile {

    private var shouldCallFail = true

    override suspend fun invoke() =
        if (shouldCallFail) Result.failure(throwable) else Result.success(profileToGive)

    fun shouldCallFail(should: Boolean) {
        shouldCallFail = should
    }
}

private const val PROFILE_LOGIN = "profileLogin"
private const val AVATAR_URL = "avatarLogin"
private const val PROFILE_NAME = "profileName"
private const val PROFILE_BIO = "profileBio"
private const val PROFILE_URL = "profileUrl"
