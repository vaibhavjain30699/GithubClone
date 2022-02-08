package com.vaibhav.githubclone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

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

        val response = viewModel.getProfileDetails(user)
        profileTitle.text = response?.name

    }

    fun initialSetup(){
        retrofitService = RetrofitService.getInstance()
        repository = GithubRepository(retrofitService)
        viewModel = ViewModelProvider(this,ViewModelFactory(repository)).get(GithubViewModel::class.java)
        user = intent.getStringExtra("username").toString()
        profileTitle = findViewById(R.id.profileTitle)
    }
}