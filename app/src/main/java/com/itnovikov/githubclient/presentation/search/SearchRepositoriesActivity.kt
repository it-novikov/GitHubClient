package com.itnovikov.githubclient.presentation.search

import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import androidx.activity.viewModels
import com.itnovikov.githubclient.core.BaseActivity
import com.itnovikov.githubclient.databinding.ActivitySearchBinding
import com.itnovikov.githubclient.presentation.downloads.DownloadsActivity

class SearchRepositoriesActivity : BaseActivity() {

    private val binding by lazy { ActivitySearchBinding.inflate(layoutInflater) }
    private val viewModel: SearchRepositoriesViewModel by viewModels()
    private val adapter = SearchRepositoriesAdapter()

    private var isReady = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        loadData()
        initRV()
        viewModel.loadRepositories("it-novikov")
        observeViewModel()
        configureButton()
        setSearchView()
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

        viewModel.getReady().observe(this) {
            isReady = it
        }
    }

    private fun setSearchView() {
        binding.searchViewUsers.clearFocus()
        binding.searchViewUsers.setOnQueryTextListener(object :
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding.searchViewUsers.clearFocus()
                val username = binding.searchViewUsers.query.toString()
                viewModel.loadRepositories(username)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }
}