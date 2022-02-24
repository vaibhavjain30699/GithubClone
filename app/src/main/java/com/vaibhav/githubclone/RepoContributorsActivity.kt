package com.vaibhav.githubclone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vaibhav.githubclone.adapter.ContributorsRecyclerViewAdapter
import com.vaibhav.githubclone.retrofitAPI.GithubRepository
import com.vaibhav.githubclone.retrofitAPI.GithubRepositoryImpl
import com.vaibhav.githubclone.retrofitAPI.RetrofitService
import com.vaibhav.githubclone.viewmodel.GithubViewModel
import com.vaibhav.githubclone.viewmodel.ViewModelFactory

class RepoContributorsActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var contributorsRecyclerViewAdapter: ContributorsRecyclerViewAdapter

    lateinit var user: String
    lateinit var repositoryName: String
    lateinit var viewModel: GithubViewModel
    lateinit var retrofitService: RetrofitService
    lateinit var repository: GithubRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo_contributors)

        supportActionBar?.title = "Contributors"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initialSetup()

        viewModel.listOfContributors.observe(this) {
            contributorsRecyclerViewAdapter = ContributorsRecyclerViewAdapter(it)
            recyclerView.adapter = contributorsRecyclerViewAdapter
        }

        viewModel.getContributorsForRepository(user, repositoryName)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return true
    }

    private fun initialSetup() {
        retrofitService = RetrofitService.getInstance()
        repository = GithubRepositoryImpl(retrofitService)
        viewModel =
            ViewModelProvider(this, ViewModelFactory(repository)).get(GithubViewModel::class.java)
        repositoryName = intent.getStringExtra(ProfileActivity.REPOSITORY_NAME_TAG).toString()
        user = intent.getStringExtra(ProfileActivity.USERNAME_TAG).toString()

        recyclerView = findViewById(R.id.contributorsRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}