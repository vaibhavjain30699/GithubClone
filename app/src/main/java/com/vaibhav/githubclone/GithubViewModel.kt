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
    var listOfRepos: MutableLiveData<List<Repository>> = MutableLiveData()
    val listOfContributors: MutableLiveData<List<Contributor>> = MutableLiveData()

    fun getProfileDetails(user: String) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.getProfileDetails(user).apply {
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

    fun getRepositoriesForUser(user: String) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.getRepositoriesForUser(user).apply {
                enqueue(object : Callback<List<Repository>> {
                    override fun onResponse(
                        call: Call<List<Repository>>,
                        response: Response<List<Repository>>
                    ) {
                        listOfRepos.value = response.body()
                    }

                    override fun onFailure(call: Call<List<Repository>>, t: Throwable) {
                        Log.d("GithubViewModel", t.toString())
                    }
                })
            }
        }
    }

    fun getContributorsForRepository(user: String, repo: String) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.getContributorsForRepository(user, repo).apply {
                enqueue(object : Callback<List<Contributor>> {
                    override fun onResponse(
                        call: Call<List<Contributor>>,
                        response: Response<List<Contributor>>
                    ) {
                        listOfContributors.value = response.body()
                    }

                    override fun onFailure(call: Call<List<Contributor>>, t: Throwable) {
                        Log.d("GithubViewModel", t.toString())
                    }
                })
            }
        }
    }
}