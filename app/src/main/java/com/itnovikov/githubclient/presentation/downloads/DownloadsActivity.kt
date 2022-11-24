package com.itnovikov.githubclient.presentation.downloads

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.itnovikov.githubclient.core.BaseActivity
import com.itnovikov.githubclient.databinding.ActivityDownloadsBinding

class DownloadsActivity : BaseActivity() {

    private val binding by lazy { ActivityDownloadsBinding.inflate(layoutInflater) }
    private val viewModel: DownloadsViewModel by viewModels()
    private val adapter = DownloadsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initRV()
        updateRV()
        setActionBar()
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, DownloadsActivity::class.java)
        }
    }

    private fun initRV() {
        binding.rvDownloads.adapter = adapter
    }

    private fun updateRV() {
        viewModel.downloads.observe(this) {
            adapter.submitList(it)
        }
    }

    private fun setActionBar() {
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }
}