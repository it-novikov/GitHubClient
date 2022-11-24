package com.itnovikov.githubclient.data.local

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.itnovikov.githubclient.data.local.model.Download

@Database(entities = [Download::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun dao(): Dao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null
        private val LOCK = Any()
        private const val DB_NAME = "downloads.db"

        fun getInstance(application: Application): AppDatabase {
            INSTANCE?.let { return it }
            synchronized(LOCK) {
                INSTANCE?.let { return it }
            }
            val db = Room.databaseBuilder(
                application.applicationContext,
                AppDatabase::class.java,
                DB_NAME
            ).build()

            INSTANCE = db
            return db
        }
    }
}