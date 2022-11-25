package com.itnovikov.githubclient.presentation.search

import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.itnovikov.githubclient.core.BaseActivity
import com.itnovikov.githubclient.databinding.ActivitySearchBinding

class SearchRepositoriesActivity : BaseActivity() {

    private val binding by lazy { ActivitySearchBinding.inflate(layoutInflater) }
    private val viewModel: SearchRepositoriesViewModel by viewModels()

    private var isReady = false

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        loadData()
        observeViewModel()
    }

    private fun loadData() {
        val content: View = findViewById(android.R.id.content)
        content.viewTreeObserver.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
            override fun onPreDraw(): Boolean {
                return if (isReady) {
                    content.viewTreeObserver.removeOnPreDrawListener(this)
                    true
                } else {
                    false
                }
            }
        })
    }

    private fun observeViewModel() {
        viewModel.getReady().observe(this) {
            isReady = it
        }
    }
}