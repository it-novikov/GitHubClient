package com.itnovikov.githubclient.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "downloads")
data class Download(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,
    @ColumnInfo(name = "username")
    val username: String = "",
    @ColumnInfo(name = "repositoryName")
    val repositoryName: String = ""
)
