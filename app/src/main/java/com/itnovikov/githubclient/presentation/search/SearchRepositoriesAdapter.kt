package com.itnovikov.githubclient.presentation.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.itnovikov.githubclient.R
import com.itnovikov.githubclient.data.remote.model.Repo

class SearchRepositoriesAdapter
    : ListAdapter<Repo, SearchRepositoriesViewHolder>(SearchRepositoriesDiffCallback()) {

    private var onItemClick: ((Repo) -> Unit)? = null
    private var onItemButtonClick: ((Repo) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchRepositoriesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.repo_item,
            parent,
            false
        )
        return SearchRepositoriesViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchRepositoriesViewHolder, position: Int) {
        val repo = getItem(position)
        holder.title.text = repo.name.toString().trim()
        if (repo.description != null) {
            holder.description.text = repo.description.toString().trim()
        }
        holder.languages.text = repo.language.toString().trim()
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(repo)
        }
        holder.buttonDownloadRepo.setOnClickListener {
            onItemButtonClick?.invoke(repo)
        }
    }

    fun setOnItemClick(function: ((Repo) -> Unit)?) {
        onItemClick = function
    }

    fun setOnItemButtonClick(function: ((Repo) -> Unit)?) {
        onItemButtonClick = function
    }

}