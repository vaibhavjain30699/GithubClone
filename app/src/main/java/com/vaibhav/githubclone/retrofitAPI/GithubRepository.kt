package com.vaibhav.githubclone.retrofitAPI

import com.vaibhav.githubclone.model.Contributor
import com.vaibhav.githubclone.model.Profile
import com.vaibhav.githubclone.model.Repository
import retrofit2.Call

interface GithubRepository {
    suspend fun getProfileDetails(user: String): Profile
    suspend fun getRepositoriesForUser(user: String): List<Repository>
    suspend fun getContributorsForRepository(user: String, repo: String): List<Contributor>
}


class GithubRepositoryImpl constructor(private val retrofitService: RetrofitService) :
    GithubRepository {

    override suspend fun getProfileDetails(user: String) = retrofitService.getProfileDetails(user)

    override suspend fun getRepositoriesForUser(user: String): List<Repository> =
        retrofitService.getRepositoriesForUser(user)

    override suspend fun getContributorsForRepository(
        user: String,
        repo: String
    ): List<Contributor> = retrofitService.getContributorsForRepository(user, repo)
}