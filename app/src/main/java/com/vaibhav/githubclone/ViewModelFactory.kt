package com.vaibhav.githubclone

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory constructor(private val repository: GithubRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(GithubViewModel::class.java)) {
            GithubViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel not found!")
        }
    }
}