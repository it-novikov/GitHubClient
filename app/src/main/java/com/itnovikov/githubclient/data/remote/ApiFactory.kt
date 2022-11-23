package com.itnovikov.githubclient.data.remote

import com.itnovikov.githubclient.data.model.Repository
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://api-github-com.translate.goog/"

class ApiFactory {

    companion object Factory {
        val instance: ApiFactory
            @Synchronized get() {
                return ApiFactory()
            }
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val apiService: ApiService = retrofit.create(ApiService::class.java)

    suspend fun getRepoList(username: String): Response<List<Repository>> {
        return apiService.loadRepositories(username)
    }
}