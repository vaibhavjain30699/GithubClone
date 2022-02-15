package com.vaibhav.githubclone.model

import com.google.gson.annotations.SerializedName

data class Contributor(
    @SerializedName("login")
    val userID: String,
    @SerializedName("avatar_url")
    val avatarURL: String,
    @SerializedName("html_url")
    val profileURL: String,
    @SerializedName("contributions")
    val noOfContributions: Int,
)
