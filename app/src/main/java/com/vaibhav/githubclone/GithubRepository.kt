package com.vaibhav.githubclone

class GithubRepository constructor(private val retrofitService: RetrofitService) {

    suspend fun getProfileDetails(user: String) = retrofitService.getProfileDetails(userID = user)

}