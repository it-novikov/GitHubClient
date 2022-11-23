package com.itnovikov.githubclient.presentation.SearchRepos

import androidx.recyclerview.widget.DiffUtil
import com.itnovikov.githubclient.data.model.Repository

class SearchRepositoriesDiffCallback: DiffUtil.ItemCallback<Repository>() {

    override fun areItemsTheSame(oldItem: Repository, newItem: Repository): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Repository, newItem: Repository): Boolean {
        return oldItem == newItem
    }
}