package com.itnovikov.githubclient.presentation.SearchRepos

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.itnovikov.githubclient.R

class SearchRepositoriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val title: TextView = itemView.findViewById(R.id.textViewRepoTitle)
    val description: TextView = itemView.findViewById(R.id.textViewRepoDescription)
    val languages: TextView = itemView.findViewById(R.id.textViewRepoLanguages)
}