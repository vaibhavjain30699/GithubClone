package com.vaibhav.githubclone

import android.graphics.Color
import android.graphics.Typeface.BOLD
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.TextUtils
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.set
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso

class ProfileActivity : AppCompatActivity() {

    lateinit var name: TextView
    lateinit var profilePic: ImageView
    lateinit var username: TextView
    lateinit var bio: TextView
    lateinit var noOfRepos: TextView
    lateinit var followers: TextView
    lateinit var following: TextView

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

        name = findViewById(R.id.profileTitle)
        profilePic = findViewById(R.id.profilePicture)
        username = findViewById(R.id.profileUserName)
        bio = findViewById(R.id.profileBio)
        noOfRepos = findViewById(R.id.noOfRepos)
        followers = findViewById(R.id.noOfFollowers)
        following = findViewById(R.id.noOfFollowing)
    }

    private fun setProfileData(profile: Profile) {
        name.text = profile.name
        username.text = TextUtils.concat("@",profile.userId)
        bio.text = profile.bio
        Picasso.get().load(profile.avatarURL).into(profilePic)
        following.text = createFormattedStringForTextView(profile.following.toString()," Following")
        followers.text = createFormattedStringForTextView(profile.followers.toString()," Followers")
        noOfRepos.text = createFormattedStringForTextView(profile.publicRepos.toString(),"Repos")
    }

    private fun createFormattedStringForTextView(text: String, suffix: String): CharSequence? {
        val tempSpanString = SpannableString(text)
        tempSpanString.setSpan(RelativeSizeSpan(1.35f),0,text.length,0)
        tempSpanString.setSpan(ForegroundColorSpan(Color.BLACK),0,text.length,0)
        tempSpanString.setSpan(StyleSpan(BOLD),0,text.length,0)
        return TextUtils.concat(tempSpanString,"\n",suffix)
    }
}