package com.itnovikov.githubclient.presentation.search

import android.app.Application
import android.app.DownloadManager
import android.content.ContentValues
import android.content.Context
import android.content.Context.DOWNLOAD_SERVICE
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import androidx.annotation.RequiresApi
import androidx.core.content.getSystemService
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.itnovikov.githubclient.data.model.ResponseModel
import com.itnovikov.githubclient.data.remote.ApiFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.net.URL

class SearchRepositoriesViewModel(application: Application): AndroidViewModel(application) {

    private val isReady = MutableLiveData<Boolean>(false)
    private val repositories = MutableLiveData<List<ResponseModel>>()

    fun getRepos(): LiveData<List<ResponseModel>> {
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
        val downloadManager: DownloadManager = context.getSystemService(DOWNLOAD_SERVICE) as DownloadManager
        downloadManager.enqueue(request)
    }
}