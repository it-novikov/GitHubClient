package com.itnovikov.githubclient.domain

import com.itnovikov.githubclient.data.local.model.Download

class AddDownloadUseCase(private val repository: Repository) {

    suspend fun addDownload(download: Download) {
        repository.addDownload(download)
    }
}