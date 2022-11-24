package com.itnovikov.githubclient.presentation.downloads

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.itnovikov.githubclient.R
import com.itnovikov.githubclient.data.local.model.Download

class DownloadsAdapter
    : ListAdapter<Download, DownloadsViewHolder>(DownloadsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DownloadsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.download_item,
            parent,
            false
        )
        return DownloadsViewHolder(view)
    }

    override fun onBindViewHolder(holder: DownloadsViewHolder, position: Int) {
        val download = getItem(position)
        holder.username.text = download.username
        holder.repoName.text = download.repositoryName
    }
}