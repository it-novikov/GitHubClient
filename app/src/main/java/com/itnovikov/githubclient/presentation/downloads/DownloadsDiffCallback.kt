package com.itnovikov.githubclient.presentation.downloads

import androidx.recyclerview.widget.DiffUtil
import com.itnovikov.githubclient.data.local.model.Download

class DownloadsDiffCallback: DiffUtil.ItemCallback<Download>() {
    override fun areItemsTheSame(oldItem: Download, newItem: Download): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Download, newItem: Download): Boolean {
        return oldItem == newItem
    }
}