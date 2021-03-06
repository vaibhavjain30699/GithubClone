package com.vaibhav.githubclone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.squareup.picasso.Picasso
import com.vaibhav.githubclone.FormatterClass.createFormattedStringForTextView
import com.vaibhav.githubclone.adapter.ViewPagerAdapter
import com.vaibhav.githubclone.model.Profile
import com.vaibhav.githubclone.retrofitAPI.GithubRepository
import com.vaibhav.githubclone.retrofitAPI.GithubRepositoryImpl
import com.vaibhav.githubclone.retrofitAPI.RetrofitService
import com.vaibhav.githubclone.viewmodel.GithubViewModel
import com.vaibhav.githubclone.viewmodel.ViewModelFactory
import kotlin.random.Random

class ProfileActivity : AppCompatActivity() {

    lateinit var name: TextView
    lateinit var profilePic: ImageView
    lateinit var username: TextView
    lateinit var bio: TextView
    lateinit var noOfRepos: TextView
    lateinit var noOfStars: TextView
    lateinit var followers: TextView
    lateinit var following: TextView
    lateinit var viewPager: ViewPager
    lateinit var tabLayout: TabLayout

    lateinit var viewPagerAdapter: ViewPagerAdapter

    lateinit var user: String
    lateinit var viewModel: GithubViewModel
    lateinit var retrofitService: RetrofitService
    lateinit var repository: GithubRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        actionBar?.hide()
        supportActionBar?.hide()

        initialSetup()

        EspressoIdlingResource.increment()
        viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        viewPager.adapter = viewPagerAdapter
        viewPager.currentItem = 1
        tabLayout.setupWithViewPager(viewPager)
        EspressoIdlingResource.decrement()

        viewModel.profile.observe(
            this, Observer {
                setProfileData(it)
            }
        )

        viewModel.getProfileDetails(user)
    }

    private fun initialSetup() {
        retrofitService = RetrofitService.getInstance()
        repository = GithubRepositoryImpl(retrofitService)
        viewModel =
            ViewModelProvider(this, ViewModelFactory(repository)).get(GithubViewModel::class.java)
        user = intent.getStringExtra("username").toString()
        viewModel.user = user
        name = findViewById(R.id.profileTitle)
        profilePic = findViewById(R.id.profilePicture)
        username = findViewById(R.id.profileUserName)
        bio = findViewById(R.id.profileBio)
        noOfRepos = findViewById(R.id.noOfRepos)
        noOfStars = findViewById(R.id.noOfStars)
        followers = findViewById(R.id.noOfFollowers)
        following = findViewById(R.id.noOfFollowing)
        viewPager = findViewById(R.id.viewPager)
        tabLayout = findViewById(R.id.tabLayout)
    }

    private fun setProfileData(profile: Profile?) {
        if (profile != null) {
            name.text = profile.name
            username.text = TextUtils.concat("@", profile.userId)
            bio.text = profile.bio
            Picasso.get().load(profile.avatarURL).into(profilePic)
            following.text =
                createFormattedStringForTextView(profile.following.toString(), " Following")
            followers.text =
                createFormattedStringForTextView(profile.followers.toString(), " Followers")
            noOfRepos.text =
                createFormattedStringForTextView(profile.publicRepos.toString(), "Repos")
            noOfStars.text =
                createFormattedStringForTextView(Random.nextInt(1, 1000000).toString(), "Stars")
        } else {
            Toast.makeText(this, "Profile Not Found!", Toast.LENGTH_LONG).show()
            finish()
        }
    }

    companion object {
        const val REPOSITORY_NAME_TAG = "repository_name"
        const val USERNAME_TAG = "username"
    }
}