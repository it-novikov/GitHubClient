package com.itnovikov.githubclient.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "downloads")
data class Download(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val username: String = "",
    val repositoryName: String = ""
)
