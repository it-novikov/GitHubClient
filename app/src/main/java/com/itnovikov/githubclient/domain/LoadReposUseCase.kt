package com.itnovikov.githubclient.domain

import com.itnovikov.githubclient.data.remote.model.Repo

class LoadReposUseCase(private val repository: Repository) {

    suspend fun loadRepos(username: String): List<Repo>? {
        return repository.loadRepos(username)
    }
}