package com.itnovikov.githubclient.presentation.search

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.itnovikov.githubclient.data.model.ResponseModel
import com.itnovikov.githubclient.data.remote.ApiFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchRepositoriesViewModel(application: Application): AndroidViewModel(application) {

    private val isReady = MutableLiveData<Boolean>(false)
    private val repositories = MutableLiveData<List<ResponseModel>>()

    fun getRepos(): LiveData<List<ResponseModel>> {
        return repositories
    }

    fun getReady(): LiveData<Boolean> {
        return isReady
    }

    fun loadRepositories(username: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = ApiFactory.apiService.loadRepositories(username)
                repositories.postValue(response.body())
                isReady.postValue(true)
            } catch (e: Exception) {
                e.stackTrace
            }
        }
    }
}