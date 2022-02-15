package com.vaibhav.githubclone.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vaibhav.githubclone.viewmodel.OverViewViewModel
import com.vaibhav.githubclone.R
import com.vaibhav.githubclone.adapter.ReposRecyclerViewAdapter
import com.vaibhav.githubclone.model.Repository

class OverViewFragment : Fragment() {

    companion object {
        fun newInstance() = OverViewFragment()
    }

    private lateinit var viewModel: OverViewViewModel
    private lateinit var pinnedReposRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.over_view_fragment, container, false)
        pinnedReposRecyclerView = view.findViewById(R.id.pinnedReposRecyclerView)
        val tempList = emptyList<Repository>()
        val adapter = ReposRecyclerViewAdapter(tempList)
        pinnedReposRecyclerView.adapter = adapter
        pinnedReposRecyclerView.layoutManager = LinearLayoutManager(view.context)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(OverViewViewModel::class.java)
        // TODO: Use the ViewModel
    }

}