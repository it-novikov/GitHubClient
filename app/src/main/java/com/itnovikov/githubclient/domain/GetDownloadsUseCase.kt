package com.itnovikov.githubclient.domain

import androidx.lifecycle.LiveData
import com.itnovikov.githubclient.data.local.model.Download

class GetDownloadsUseCase(private val repository: Repository) {

    fun getDownloads(): LiveData<List<Download>> {
        return repository.getDownloads()
    }
}