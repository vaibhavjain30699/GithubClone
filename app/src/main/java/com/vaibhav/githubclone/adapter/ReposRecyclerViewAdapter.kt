package com.vaibhav.githubclone.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.vaibhav.githubclone.FormatterClass
import com.vaibhav.githubclone.ProfileActivity
import com.vaibhav.githubclone.R
import com.vaibhav.githubclone.RepoContributorsActivity
import com.vaibhav.githubclone.model.Repository

class ReposRecyclerViewAdapter constructor(private val listOfPinnedRepos: List<Repository>) :
    RecyclerView.Adapter<ReposRecyclerViewAdapter.ReposViewHolder>() {

    class ReposViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.title)
        val subTitle: TextView = itemView.findViewById(R.id.subTitle)
        val topicTags: ChipGroup = itemView.findViewById(R.id.tags)
        val language: TextView = itemView.findViewById(R.id.language)
        val stars: TextView = itemView.findViewById(R.id.starsCount)
        val forks: TextView = itemView.findViewById(R.id.forks)
        val lastUpdated: TextView = itemView.findViewById(R.id.lastUpdatedStatus)
        val repositoryCardView: CardView = itemView.findViewById(R.id.repositoryCardView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReposViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_repository, parent, false)
        return ReposViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReposViewHolder, position: Int) {
        val tempRepo = listOfPinnedRepos[position]
        holder.title.text = tempRepo.name
        holder.subTitle.text = tempRepo.description ?: "No Description"
        holder.topicTags.isSingleLine = true
        for (chipData in tempRepo.topics) {
            val chipTemp = Chip(holder.itemView.context)
            chipTemp.text = chipData
            holder.topicTags.addView(chipTemp)
        }
        holder.language.text = tempRepo.language
        holder.stars.text = tempRepo.stargazers_count.toString()
        holder.forks.text = tempRepo.forks_count.toString()
        holder.lastUpdated.text = FormatterClass.getUpdatedStatusWithGiveDate(tempRepo.lastUpdated)
        holder.repositoryCardView.setOnClickListener {
            val intent = Intent(holder.itemView.context, RepoContributorsActivity::class.java)
            intent.putExtra(ProfileActivity.REPOSITORY_NAME_TAG, tempRepo.name)
            intent.putExtra(ProfileActivity.USERNAME_TAG, tempRepo.owner.userID)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return listOfPinnedRepos.size
    }
}