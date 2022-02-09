package com.vaibhav.githubclone

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.withContext

class ProfileActivity : AppCompatActivity() {

    lateinit var profileTitle: TextView
    lateinit var user: String
    lateinit var viewModel: GithubViewModel
    lateinit var retrofitService: RetrofitService
    lateinit var repository: GithubRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        initialSetup()

        viewModel.profile.observe(
            this, Observer {
                Log.d("vaibhav123", it.toString())
                setProfileData(it)
            }
        )

        viewModel.getProfileDetails("vaibhavjain30699")
    }

    private fun initialSetup() {
        retrofitService = RetrofitService.getInstance()
        repository = GithubRepositoryImpl(retrofitService)
        viewModel =
            ViewModelProvider(this, ViewModelFactory(repository)).get(GithubViewModel::class.java)
        user = intent.getStringExtra("username").toString()
        profileTitle = findViewById(R.id.profileTitle)
    }

    private fun setProfileData(profile: Profile) {
        profileTitle.text = profile.name
    }
}