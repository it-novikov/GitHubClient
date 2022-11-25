package com.itnovikov.githubclient.presentation.search

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.itnovikov.githubclient.R
import com.itnovikov.githubclient.data.local.model.DownloadMapper
import com.itnovikov.githubclient.databinding.FragmentSearchRepositoriesBinding
import com.itnovikov.githubclient.presentation.downloads.DownloadsFragment

class SearchRepositoriesFragment : Fragment() {

    private lateinit var binding: FragmentSearchRepositoriesBinding
    private val viewModel: SearchRepositoriesViewModel by viewModels()
    private val adapter = SearchRepositoriesAdapter()

    private var isReady = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchRepositoriesBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRV()
        context?.let { viewModel.loadRepositories(it,"it-novikov") }
        observeViewModel()
        configureButton()
        context?.let { setSearchView(it) }
    }

    private fun initRV() {
        binding.rvRepositories.adapter = adapter
        initCallbacks()
    }

    private fun initCallbacks() {
        adapter.setOnItemClick {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(it.htmlUrl)))
        }

        adapter.setOnItemButtonClick {
            context?.let { context -> viewModel.createUrl(context, it) }
            val download = DownloadMapper.mapRepoToDownload(it)
            viewModel.addDownload(download)
        }
    }

    private fun configureButton() {
        binding.buttonGoDownloads.setOnClickListener {
            val fragment = DownloadsFragment()
            parentFragmentManager
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.fragmentContainer, fragment)
                .commit()
        }
    }

    private fun observeViewModel() {
        viewModel.getRepos().observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        viewModel.getReady().observe(viewLifecycleOwner) {
            isReady = it
        }

        viewModel.getError().observe(viewLifecycleOwner) {
            Snackbar.make(binding.root, it, Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun setSearchView(context: Context) {
        binding.searchViewUsers.clearFocus()
        binding.searchViewUsers.setOnQueryTextListener(object :
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding.searchViewUsers.clearFocus()
                val username = binding.searchViewUsers.query.toString()
                viewModel.loadRepositories(context, username)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }
}