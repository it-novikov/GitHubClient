package com.itnovikov.githubclient.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.itnovikov.githubclient.databinding.ActivityMainBinding
import com.itnovikov.githubclient.presentation.SearchRepos.SearchRepositoriesActivity

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        Thread.sleep(2000)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        startActivity(SearchRepositoriesActivity.newIntent(this))
    }
}