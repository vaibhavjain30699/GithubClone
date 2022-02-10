package com.vaibhav.githubclone

import com.google.gson.annotations.SerializedName

data class Contributor(
    @SerializedName("login")
    val userID: String,
    val avatarURL: String,
    @SerializedName("html_url")
    val profileURL: String,
    @SerializedName("contributions")
    val noOfContributions: Int,
)
