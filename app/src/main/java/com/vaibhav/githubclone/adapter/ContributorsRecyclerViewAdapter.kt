package com.vaibhav.githubclone.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso
import com.vaibhav.githubclone.R
import com.vaibhav.githubclone.model.Contributor

class ContributorsRecyclerViewAdapter constructor(private val contributorsList: List<Contributor>) :
    RecyclerView.Adapter<ContributorsRecyclerViewAdapter.ContributorsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContributorsViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_contributor, parent, false)
        return ContributorsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContributorsViewHolder, position: Int) {
        val tempContributor = contributorsList[position]
        holder.contributorID.text = tempContributor.userID
        holder.contributions.text = "${tempContributor.noOfContributions} contributions"
        Picasso.get().load(tempContributor.avatarURL).into(holder.contributorImage)
        holder.viewProfile.setOnClickListener {
            val uri = Uri.parse(tempContributor.profileURL)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = contributorsList.size

    class ContributorsViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val contributorID: TextView = itemView.findViewById(R.id.contributorID)
        val contributions: TextView = itemView.findViewById(R.id.contributions)
        val viewProfile: Button = itemView.findViewById(R.id.viewProfileButton)
        val contributorImage: ShapeableImageView = itemView.findViewById(R.id.contributorImage)
    }

}