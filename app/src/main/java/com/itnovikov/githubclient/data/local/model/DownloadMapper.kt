package com.itnovikov.githubclient.data.local.model

import com.itnovikov.githubclient.data.remote.model.Repo

class DownloadMapper {
    companion object {
        fun mapRepoToDownload(repo: Repo): Download = Download(
            username = repo.owner?.login.toString().trim(),
            repositoryName = repo.name.toString().trim()
        )
    }
}