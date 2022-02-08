package com.vaibhav.githubclone

data class Profile(
    val name: String,
    val company: String?,
    val location: String,
    val followers: Int,
    val following: Int,
    val publicRepos: Int,
    val avatarURL: String,
    val bio: String?
)
