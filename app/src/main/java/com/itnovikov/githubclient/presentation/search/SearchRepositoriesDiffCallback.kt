package com.itnovikov.githubclient.presentation.search

import androidx.recyclerview.widget.DiffUtil
import com.itnovikov.githubclient.data.model.ResponseModel

class SearchRepositoriesDiffCallback: DiffUtil.ItemCallback<ResponseModel>() {

    override fun areItemsTheSame(oldItem: ResponseModel, newItem: ResponseModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ResponseModel, newItem: ResponseModel): Boolean {
        return oldItem == newItem
    }
}