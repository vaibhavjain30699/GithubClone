package com.vaibhav.githubclone.model

import com.google.gson.annotations.SerializedName

data class Repository(
    val id: Int,
    val name: String,
    @SerializedName("html_url")
    val URL: String,
    val description: String?,
    val language: String,
    val topics: List<String>,
    val stargazers_count: Int,
    val forks_count: Int,
    @SerializedName("updated_at")
    val lastUpdated: String,
    val owner: Owner,
)

data class Owner(
    @SerializedName("login")
    val userID: String
)
