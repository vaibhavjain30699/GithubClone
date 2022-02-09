package com.vaibhav.githubclone

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GithubViewModel constructor(private val repository: GithubRepository) : ViewModel() {

    var profile: MutableLiveData<Profile> = MutableLiveData()

    fun getProfileDetails(user: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val call = repository.getProfileDetails(user).apply {
                enqueue(object : Callback<Profile> {
                    override fun onResponse(call: Call<Profile>, response: Response<Profile>) {
                        profile.value = response.body()
                    }

                    override fun onFailure(call: Call<Profile>, t: Throwable) {
                        Log.d("GithubViewModel", t.toString())
                    }
                })
            }
        }
    }
}