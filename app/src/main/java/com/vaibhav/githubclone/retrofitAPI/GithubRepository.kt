package com.vaibhav.githubclone.retrofitAPI

import com.vaibhav.githubclone.model.Contributor
import com.vaibhav.githubclone.model.Profile
import com.vaibhav.githubclone.model.Repository
import retrofit2.Response

interface GithubRepository {
    suspend fun getProfileDetails(user: String): Response<Profile>
    suspend fun getRepositoriesForUser(user: String): Response<List<Repository>>
    suspend fun getContributorsForRepository(
        user: String,
        repo: String
    ): Response<List<Contributor>>
}


class GithubRepositoryImpl constructor(private val retrofitService: RetrofitService) :
    GithubRepository {

    override suspend fun getProfileDetails(user: String) = retrofitService.getProfileDetails(user)

    override suspend fun getRepositoriesForUser(user: String) =
        retrofitService.getRepositoriesForUser(user)

    override suspend fun getContributorsForRepository(
        user: String,
        repo: String
    ) = retrofitService.getContributorsForRepository(user, repo)
}