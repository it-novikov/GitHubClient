package com.itnovikov.githubclient.presentation.search

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.itnovikov.githubclient.R

class SearchRepositoriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val title: TextView = itemView.findViewById(R.id.textViewRepoTitle)
    val description: TextView = itemView.findViewById(R.id.textViewRepoDescription)
    val languages: TextView = itemView.findViewById(R.id.textViewRepoLanguages)
    val buttonDownloadRepo: Button = itemView.findViewById(R.id.buttonDownloadRepo)
}