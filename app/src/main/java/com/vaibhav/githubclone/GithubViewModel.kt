package com.vaibhav.githubclone

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher

class GithubViewModel constructor(private val repository: GithubRepository) : ViewModel() {

    var profile: Profile? = null

    fun getProfileDetails(user: String) : Profile? {
        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.getProfileDetails(user)
            withContext(Dispatchers.IO){
                if(response.isSuccessful){
                    profile = response.body()
                }
            }
        }
        return profile
    }
}