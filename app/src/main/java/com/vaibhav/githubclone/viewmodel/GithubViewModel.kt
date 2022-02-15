package com.vaibhav.githubclone.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vaibhav.githubclone.retrofitAPI.GithubRepository
import com.vaibhav.githubclone.model.Contributor
import com.vaibhav.githubclone.model.Profile
import com.vaibhav.githubclone.model.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GithubViewModel constructor(private val repository: GithubRepository) : ViewModel() {

    private val _profile: MutableLiveData<Profile> = MutableLiveData()
    val profile: LiveData<Profile> = _profile
    private var _listOfRepos: MutableLiveData<List<Repository>> = MutableLiveData()
    val listOfRepos: LiveData<List<Repository>> = _listOfRepos
    private var _listOfContributors: MutableLiveData<List<Contributor>> = MutableLiveData()
    val listOfContributors: LiveData<List<Contributor>> = _listOfContributors

    fun getProfileDetails(user: String) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.getProfileDetails(user).let { profileTemp ->
                _profile.postValue(profileTemp)
            }
        }
    }

    fun getRepositoriesForUser(user: String): LiveData<List<Repository>> {
        CoroutineScope(Dispatchers.IO).launch {
            repository.getRepositoriesForUser(user).let { tempList ->
                _listOfRepos.postValue(tempList)
            }
        }
        return listOfRepos
    }

    fun getContributorsForRepository(user: String, repo: String) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.getContributorsForRepository(user, repo).let { tempList ->
                _listOfContributors.postValue(tempList)
            }
        }
    }
}