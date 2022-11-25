package com.itnovikov.githubclient.presentation.search

import android.app.Application
import android.app.DownloadManager
import android.content.Context
import android.content.Context.DOWNLOAD_SERVICE
import android.net.Uri
import android.os.Environment
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.itnovikov.githubclient.data.local.model.Download
import com.itnovikov.githubclient.data.remote.model.Repo
import com.itnovikov.githubclient.data.repository.RepositoryImpl
import com.itnovikov.githubclient.domain.AddDownloadUseCase
import com.itnovikov.githubclient.domain.LoadReposUseCase
import kotlinx.coroutines.launch

class SearchRepositoriesViewModel(application: Application): AndroidViewModel(application) {

    private val repository = RepositoryImpl(application)
    private val addDownloadsUseCase = AddDownloadUseCase(repository)
    private val loadReposUseCase = LoadReposUseCase(repository)

    private val isReady = MutableLiveData(false)
    private val repositories = MutableLiveData<List<Repo>>()

    fun getRepos(): LiveData<List<Repo>> {
        return repositories
    }

    fun getReady(): LiveData<Boolean> {
        return isReady
    }

    fun loadRepositories(username: String) {
        viewModelScope.launch {
            try {
                val response = loadReposUseCase.loadRepos(username) ?: return@launch
                repositories.postValue(response)
                isReady.postValue(true)
            } catch (e: Exception) {
                e.stackTrace
            }
        }
    }

    fun saveRepo(context: Context, url: String, fileName: String) {
        val request = DownloadManager.Request(Uri.parse(url))
        request.setTitle(fileName)
        request.setMimeType("application/zip")
        request.setAllowedOverMetered(true)
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName)
        val downloadManager = context.getSystemService(DOWNLOAD_SERVICE) as DownloadManager
        downloadManager.enqueue(request)
    }

    fun addDownload(download: Download) {
        viewModelScope.launch {
            addDownloadsUseCase.addDownload(download)
        }
    }
}