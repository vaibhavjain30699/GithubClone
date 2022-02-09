package com.vaibhav.githubclone

import retrofit2.Call
import retrofit2.Response

interface GithubRepository {

    suspend fun getProfileDetails(user: String): Call<Profile>
}


class GithubRepositoryImpl constructor(private val retrofitService: RetrofitService) :
    GithubRepository {

    override suspend fun getProfileDetails(user: String) = retrofitService.getProfileDetails(user)
}