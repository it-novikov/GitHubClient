package com.itnovikov.githubclient.presentation.SearchRepos

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.itnovikov.githubclient.core.BaseActivity
import com.itnovikov.githubclient.databinding.ActivitySearchBinding
import com.itnovikov.githubclient.presentation.Downloads.DownloadsActivity

class SearchRepositoriesActivity : BaseActivity() {

    private val binding by lazy { ActivitySearchBinding.inflate(layoutInflater) }
    private val viewModel: SearchRepositoriesViewModel by viewModels()
    private val adapter = SearchRepositoriesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initRV()
        observeViewModel()
        configureButton()
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, SearchRepositoriesActivity::class.java)
        }
    }

    private fun initRV() {
        binding.rvRepositories.adapter = adapter
    }

    private fun configureButton() {
        binding.buttonGoDownloads.setOnClickListener { DownloadsActivity.newIntent(this) }
    }

    private fun observeViewModel() {
        viewModel.getRepos().observe(this) {
            adapter.submitList(it)
        }
    }
}