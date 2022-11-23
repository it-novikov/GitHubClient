package com.itnovikov.githubclient.data.remote

import com.itnovikov.githubclient.data.model.Repository
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("users/{user}/repos")
    suspend fun loadRepositories(@Path("user") user: String): Response<List<Repository>>
}