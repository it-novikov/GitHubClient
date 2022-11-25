package com.itnovikov.githubclient.presentation.downloads

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.itnovikov.githubclient.databinding.FragmentDownloadsBinding

class DownloadsFragment : Fragment() {

    private lateinit var binding: FragmentDownloadsBinding
    private val viewModel: DownloadsViewModel by viewModels()
    private val adapter = DownloadsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDownloadsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRV()
        updateRV()
    }

    private fun initRV() {
        binding.rvDownloads.adapter = adapter
    }

    private fun updateRV() {
        viewModel.downloads.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }
}