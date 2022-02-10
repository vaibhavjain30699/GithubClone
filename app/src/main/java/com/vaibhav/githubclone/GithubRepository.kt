package com.vaibhav.githubclone

import retrofit2.Call

interface GithubRepository {
    suspend fun getProfileDetails(user: String): Call<Profile>
    suspend fun getRepositoriesForUser(user: String): Call<List<Repository>>
    suspend fun getContributorsForRepository(user: String, repo: String): Call<List<Contributor>>
}


class GithubRepositoryImpl constructor(private val retrofitService: RetrofitService) :
    GithubRepository {

    override suspend fun getProfileDetails(user: String) = retrofitService.getProfileDetails(user)

    override suspend fun getRepositoriesForUser(user: String): Call<List<Repository>> =
        retrofitService.getRepositoriesForUser(user)

    override suspend fun getContributorsForRepository(
        user: String,
        repo: String
    ): Call<List<Contributor>> = retrofitService.getContributorsForRepository(user, repo)
}