package com.vaibhav.githubclone

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class ReposRecyclerViewAdapter constructor(private val listOfPinnedRepos: List<Repository>) :
    RecyclerView.Adapter<ReposRecyclerViewAdapter.PinnedReposViewHolder>() {

    class PinnedReposViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.title)
        val subTitle: TextView = itemView.findViewById(R.id.subTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PinnedReposViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_repository, parent, false)
        return PinnedReposViewHolder(view)
    }

    override fun onBindViewHolder(holder: PinnedReposViewHolder, position: Int) {
        val tempRepo = listOfPinnedRepos[position]
        holder.title.text = tempRepo.name
        holder.subTitle.text = tempRepo.description
    }

    override fun getItemCount(): Int {
        return listOfPinnedRepos.size
    }
}