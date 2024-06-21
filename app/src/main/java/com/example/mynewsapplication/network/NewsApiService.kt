package com.example.mynewsapplication.network

import com.example.mynewsapplication.model.NewsResponse
import com.example.mynewsapplication.utils.Constant.API_KEY
import com.example.mynewsapplication.utils.Constant.CATEGORY
import com.example.mynewsapplication.utils.Constant.COUNTRY
import com.example.mynewsapplication.utils.Constant.NEWS_API_HEADLINES
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    @GET(NEWS_API_HEADLINES)
    suspend fun getTopHeadlines(
        @Query(COUNTRY) country: String,
        @Query(CATEGORY) category: String,
        @Query(API_KEY) apiKey: String
    ): NewsResponse
}
