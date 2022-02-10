package com.vaibhav.githubclone

import com.google.gson.annotations.SerializedName

data class Repository(
    val id: Int,
    val name: String,
    @SerializedName("html_url")
    val URL: String,
    val description: String,
    val language: String,
    val topics: List<String>,
)
