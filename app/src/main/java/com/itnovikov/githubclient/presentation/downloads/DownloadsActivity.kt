package com.itnovikov.githubclient.presentation.downloads

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.itnovikov.githubclient.databinding.ActivityDownloadsBinding

class DownloadsActivity : AppCompatActivity() {

    private val binding by lazy { ActivityDownloadsBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, DownloadsActivity::class.java)
        }
    }
}