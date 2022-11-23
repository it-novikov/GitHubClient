package com.itnovikov.githubclient.presentation.SearchRepos

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.itnovikov.githubclient.data.model.Repository
import com.itnovikov.githubclient.data.remote.ApiFactory
import kotlinx.coroutines.launch

class SearchRepositoriesViewModel(application: Application): AndroidViewModel(application) {

    private val nameOfUser = "it-novikov"

    private val apiFactory = ApiFactory.instance
    private val repositories = MutableLiveData<List<Repository>>()

    init {
        loadRepositories()
    }

    fun getRepos(): LiveData<List<Repository>> {
        return repositories
    }

    private fun loadRepositories() {
        viewModelScope.launch {
            try {
                val response = apiFactory.getRepoList(nameOfUser)
                repositories.postValue(response.body())
            } catch (e: Exception) {
                e.stackTrace
            }
        }
    }
}