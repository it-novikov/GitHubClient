package com.itnovikov.githubclient.presentation.search

import android.app.Application
import android.app.DownloadManager
import android.content.Context
import android.content.Context.DOWNLOAD_SERVICE
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.CountDownTimer
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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SearchRepositoriesViewModel(application: Application): AndroidViewModel(application) {

    private val repository = RepositoryImpl(application)
    private val addDownloadsUseCase = AddDownloadUseCase(repository)
    private val loadReposUseCase = LoadReposUseCase(repository)

    private val isReady = MutableLiveData(false)
    private val repositories = MutableLiveData<List<Repo>>()
    private val error = MutableLiveData<String>()

    private val timer = object : CountDownTimer(3000, 1000) {
        override fun onTick(p0: Long) {}

        override fun onFinish() {
            isReady.postValue(true)
        }
    }

    init {
        timer.start()
    }

    fun getRepos(): LiveData<List<Repo>> {
        return repositories
    }

    fun getReady(): LiveData<Boolean> {
        return isReady
    }

    fun getError(): LiveData<String> {
        return error
    }

    fun loadRepositories(context: Context, username: String) {
        if (isOnline(context)) {
            viewModelScope.launch(Dispatchers.IO) {
                try {
                    val response = loadReposUseCase.loadRepos(username) ?: return@launch
                    repositories.postValue(response)
                    isReady.postValue(true)
                    timer.cancel()
                } catch (e: Exception) {
                    isReady.postValue(true)
                    error.postValue(e.toString())
                    timer.cancel()
                }
            }
        } else {
//            viewModelScope.launch(Dispatchers.IO) {
//                delay(2000)
//                isReady.postValue(true)
//                error.postValue("No internet connection")
//                timer.cancel()
//            }
        }
    }

    private fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                return true
            }
        }
        return false
    }

    private fun saveRepo(context: Context, url: String, fileName: String) {
        val request = DownloadManager.Request(Uri.parse(url))
        request.setTitle(fileName)
        request.setMimeType("application/zip")
        request.setAllowedOverMetered(true)
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName)
        val downloadManager = context.getSystemService(DOWNLOAD_SERVICE) as DownloadManager
        downloadManager.enqueue(request)
    }

    fun createUrl(context: Context, repo: Repo) {
        val owner = repo.owner?.login.toString()
        val url = "https://github.com/" + owner + "/" + repo.name +
                "/archive/refs/heads/" + repo.defaultBranch.toString() + ".zip"
        saveRepo(context, url, repo.name.toString())
    }

    fun addDownload(download: Download) {
        viewModelScope.launch {
            addDownloadsUseCase.addDownload(download)
        }
    }
}