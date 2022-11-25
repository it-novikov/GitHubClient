package com.itnovikov.githubclient.domain

import androidx.lifecycle.LiveData
import com.itnovikov.githubclient.data.local.model.Download
import com.itnovikov.githubclient.data.remote.model.Repo

interface Repository {

    fun getDownloads(): LiveData<List<Download>>

    suspend fun loadRepos(username: String): List<Repo>?

    suspend fun addDownload(download: Download)
}