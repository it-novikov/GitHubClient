package com.itnovikov.githubclient.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.itnovikov.githubclient.data.local.AppDatabase
import com.itnovikov.githubclient.data.local.model.Download
import com.itnovikov.githubclient.data.remote.ApiFactory
import com.itnovikov.githubclient.data.remote.model.Repo
import com.itnovikov.githubclient.domain.Repository

class RepositoryImpl(application: Application) : Repository {

    private val appDatabase by lazy { AppDatabase.getInstance(application).dao() }
    private val apiService by lazy { ApiFactory.apiService }

    override fun getDownloads(): LiveData<List<Download>> = appDatabase.getDownloads()

    override suspend fun addDownload(download: Download) {
        appDatabase.addDownload(download)
    }

    override suspend fun loadRepos(username: String): List<Repo>? {
        return apiService.loadRepositories(username).body()
    }
}