package com.vaibhav.githubclone

import com.google.gson.annotations.SerializedName

data class Profile(
    @SerializedName("login")
    val userId: String,
    val name: String,
    val company: String?,
    val location: String,
    val followers: Int,
    val following: Int,
    @SerializedName("public_repos")
    val publicRepos: Int,
    @SerializedName("avatar_url")
    val avatarURL: String,
    val bio: String?
)
