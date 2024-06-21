package com.example.mynewsapplication.ui.screens

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.example.mynewsapplication.ui.theme.MyNewsApplicationTheme
import com.example.mynewsapplication.utils.BiometricAuthenticationCallback
import com.example.mynewsapplication.utils.BiometricUtils
import com.example.mynewsapplication.viewmodel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), BiometricAuthenticationCallback {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BiometricUtils.checkBiometricSupport(this, this)
    }

    override fun onAuthenticationSuccess() {
        val viewModel = ViewModelProvider(this)[NewsViewModel::class.java]
        // Run the UI setup upon authentication success
        runOnUiThread {
            setContent {
                MyNewsApplicationTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colors.background
                    ) {
                        NewsApp(viewModel)
                    }
                }
            }
        }
    }
}
