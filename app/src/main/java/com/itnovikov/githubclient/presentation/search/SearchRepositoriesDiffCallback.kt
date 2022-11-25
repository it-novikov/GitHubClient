package com.itnovikov.githubclient.presentation.search

import androidx.recyclerview.widget.DiffUtil
import com.itnovikov.githubclient.data.remote.model.Repo

class SearchRepositoriesDiffCallback: DiffUtil.ItemCallback<Repo>() {

    override fun areItemsTheSame(oldItem: Repo, newItem: Repo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Repo, newItem: Repo): Boolean {
        return oldItem == newItem
    }
}