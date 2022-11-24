package com.itnovikov.githubclient.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.itnovikov.githubclient.data.local.model.Download

@Dao
interface Dao {

    @Query("SELECT * FROM downloads")
    fun getDownloads(): LiveData<List<Download>>

    @Insert
    suspend fun add(download: Download)
}