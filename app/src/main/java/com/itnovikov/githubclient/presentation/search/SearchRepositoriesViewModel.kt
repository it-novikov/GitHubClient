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
import com.itnovikov.githubclient.data.local.AppDatabase
import com.itnovikov.githubclient.data.local.model.Download
import com.itnovikov.githubclient.data.remote.model.Repository
import com.itnovikov.githubclient.data.remote.ApiFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchRepositoriesViewModel(application: Application): AndroidViewModel(application) {

    private val appDatabase by lazy { AppDatabase.getInstance(application).dao() }

    private val isReady = MutableLiveData(false)
    private val repositories = MutableLiveData<List<Repository>>()

    fun getRepos(): LiveData<List<Repository>> {
        return repositories
    }

    fun getReady(): LiveData<Boolean> {
        return isReady
    }

    fun loadRepositories(username: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = ApiFactory.apiService.loadRepositories(username)
                repositories.postValue(response.body())
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
            appDatabase.add(download)
        }
    }
}