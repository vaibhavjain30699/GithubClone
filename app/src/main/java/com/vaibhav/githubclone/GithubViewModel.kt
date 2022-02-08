package com.vaibhav.githubclone

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GithubViewModel constructor(private val repository: GithubRepository) : ViewModel() {

    var profile: Profile? = null

    fun getProfileDetails(user: String): Profile? {
        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.getProfileDetails(user)
            Log.d("response_body", response.body().toString())
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    profile = response.body()
                }
            }
        }
        return profile
    }
}