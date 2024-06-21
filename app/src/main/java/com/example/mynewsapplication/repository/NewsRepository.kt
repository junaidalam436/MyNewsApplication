package com.example.mynewsapplication.repository

import com.example.mynewsapplication.database.NewsDao
import com.example.mynewsapplication.model.Article
import com.example.mynewsapplication.network.NewsApiService
import com.example.mynewsapplication.utils.Constant.CATEGORY_BUSINESS
import com.example.mynewsapplication.utils.Constant.COUNTRY_US
import com.example.mynewsapplication.utils.Constant.NEWS_API_KEY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class NewsRepository @Inject constructor(
    private val newsDao: NewsDao,
    private val newsApi: NewsApiService
) {
    fun getNews(): Flow<List<Article>> = flow {
        val news = newsApi.getTopHeadlines(
            country = COUNTRY_US,
            category = CATEGORY_BUSINESS,
            apiKey = NEWS_API_KEY
        )
        newsDao.insertAll(news.articles)
        emit(newsDao.getAllArticles())
    }.flowOn(Dispatchers.IO)
}