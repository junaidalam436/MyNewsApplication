package com.example.mynewsapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mynewsapplication.model.Article
import com.example.mynewsapplication.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {

    private val _news = MutableStateFlow<List<Article>>(emptyList())
    val news: StateFlow<List<Article>> get() = _news

    init {
        viewModelScope.launch {
            newsRepository.getNews().collect {
                _news.value = it
            }
        }
    }
}
