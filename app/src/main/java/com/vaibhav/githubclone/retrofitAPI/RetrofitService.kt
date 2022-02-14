package com.vaibhav.githubclone.retrofitAPI

import com.vaibhav.githubclone.model.Contributor
import com.vaibhav.githubclone.model.Profile
import com.vaibhav.githubclone.model.Repository
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitService {

    @GET("users/{user}")
    suspend fun getProfileDetails(@Path("user") userID: String): Profile

    @GET("users/{user}/repos")
    fun getRepositoriesForUser(@Path("user") userID: String): Call<List<Repository>>

    @GET("repos/{user}/{repo}/contributors")
    fun getContributorsForRepository(
        @Path("user") userID: String,
        @Path("repo") repoName: String
    ): Call<List<Contributor>>

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