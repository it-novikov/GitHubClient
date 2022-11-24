package com.itnovikov.githubclient.presentation.downloads

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.itnovikov.githubclient.R

class DownloadsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val username: TextView = itemView.findViewById(R.id.textViewUsername)
    val repoName: TextView = itemView.findViewById(R.id.textViewRepoName)
}