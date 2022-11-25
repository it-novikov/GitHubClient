package com.itnovikov.githubclient.presentation.downloads

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.itnovikov.githubclient.data.local.model.Download
import com.itnovikov.githubclient.data.repository.RepositoryImpl
import com.itnovikov.githubclient.domain.GetDownloadsUseCase

class DownloadsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = RepositoryImpl(application)
    private val getDownloadsUseCase = GetDownloadsUseCase(repository)

    val downloads: LiveData<List<Download>> = getDownloadsUseCase.getDownloads()
}