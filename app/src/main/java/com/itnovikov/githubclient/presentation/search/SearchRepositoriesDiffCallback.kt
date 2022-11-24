package com.itnovikov.githubclient.presentation.search

import androidx.recyclerview.widget.DiffUtil
import com.itnovikov.githubclient.data.remote.model.Repository

class SearchRepositoriesDiffCallback: DiffUtil.ItemCallback<Repository>() {

    override fun areItemsTheSame(oldItem: Repository, newItem: Repository): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Repository, newItem: Repository): Boolean {
        return oldItem == newItem
    }
}