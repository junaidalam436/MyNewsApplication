package com.example.mynewsapplication.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.mynewsapplication.viewmodel.NewsViewModel

@Composable
fun NewsApp(viewModel: NewsViewModel) {
    val news by viewModel.news.collectAsState()
    LazyColumn {
        items(news) { article ->
            NewsCard(article)
        }
    }
}