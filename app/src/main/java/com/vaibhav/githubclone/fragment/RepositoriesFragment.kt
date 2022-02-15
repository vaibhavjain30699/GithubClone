package com.vaibhav.githubclone.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vaibhav.githubclone.R
import com.vaibhav.githubclone.adapter.ReposRecyclerViewAdapter
import com.vaibhav.githubclone.viewmodel.GithubViewModel
import com.vaibhav.githubclone.viewmodel.RepositoriesViewModel

class RepositoriesFragment : Fragment() {

    companion object {
        fun newInstance() = RepositoriesFragment()
    }

    private lateinit var viewModel: RepositoriesViewModel
    private lateinit var githubViewModel: GithubViewModel
    private lateinit var reposRecyclerView: RecyclerView
    private lateinit var reposRecyclerViewAdapter: ReposRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.repositories_fragment, container, false)
        reposRecyclerView = view.findViewById(R.id.allReposRecyclerView)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RepositoriesViewModel::class.java)
        reposRecyclerViewAdapter = ReposRecyclerViewAdapter(emptyList())
        reposRecyclerView.layoutManager = LinearLayoutManager(this.context)
        reposRecyclerView.adapter = reposRecyclerViewAdapter
        githubViewModel =
            ViewModelProvider(activity as AppCompatActivity).get(GithubViewModel::class.java)
        githubViewModel.listOfRepos.observe(viewLifecycleOwner) { tempList ->
            reposRecyclerViewAdapter = ReposRecyclerViewAdapter(tempList)
            reposRecyclerView.adapter = reposRecyclerViewAdapter
        }
        githubViewModel.getRepositoriesForUser("vaibhavjain30699")
        // TODO: Use the ViewModel
    }

}