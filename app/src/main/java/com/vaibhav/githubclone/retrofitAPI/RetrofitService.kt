package com.vaibhav.githubclone.retrofitAPI

import com.vaibhav.githubclone.model.Contributor
import com.vaibhav.githubclone.model.Profile
import com.vaibhav.githubclone.model.Repository
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitService {

    @GET("users/{user}")
    suspend fun getProfileDetails(@Path("user") userID: String): Response<Profile>

    @GET("users/{user}/repos")
    suspend fun getRepositoriesForUser(@Path("user") userID: String): Response<List<Repository>>

    @GET("repos/{user}/{repo}/contributors")
    suspend fun getContributorsForRepository(
        @Path("user") userID: String,
        @Path("repo") repoName: String
    ): Response<List<Contributor>>

    companion object {
        var retrofitService: RetrofitService? = null

        fun getInstance(): RetrofitService {

            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://api.github.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(OkHttpClient.Builder().build())
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }
    }
}