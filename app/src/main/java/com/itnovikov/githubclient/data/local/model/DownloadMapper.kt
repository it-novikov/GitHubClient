package com.itnovikov.githubclient.data.local.model

import com.itnovikov.githubclient.data.remote.model.Repository

class DownloadMapper {
    companion object {
        fun mapRepoToDownload(repo: Repository): Download = Download(
            username = repo.owner?.login.toString().trim(),
            repositoryName = repo.name.toString().trim()
        )
    }
}