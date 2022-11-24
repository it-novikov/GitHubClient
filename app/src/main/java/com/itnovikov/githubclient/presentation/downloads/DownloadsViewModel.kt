package com.itnovikov.githubclient.presentation.downloads

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.itnovikov.githubclient.data.local.AppDatabase
import com.itnovikov.githubclient.data.local.model.Download

class DownloadsViewModel(application: Application) : AndroidViewModel(application) {

    private val appDatabase by lazy { AppDatabase.getInstance(application).dao() }

    val downloads: LiveData<List<Download>> = appDatabase.getDownloads()
}