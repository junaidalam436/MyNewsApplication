package com.example.mynewsapplication.di.module

import android.app.Application
import androidx.room.Room
import com.example.mynewsapplication.database.NewsDao
import com.example.mynewsapplication.database.NewsDatabase
import com.example.mynewsapplication.network.NewsApiService
import com.example.mynewsapplication.utils.Constant.BASE_URL
import com.example.mynewsapplication.utils.Constant.DB_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * [AppModule]
 * This object provides the dependency
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNewsDatabase(app: Application): NewsDatabase {
        return Room.databaseBuilder(app, NewsDatabase::class.java, DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsDao(db: NewsDatabase): NewsDao = db.newsDao()

    @Provides
    @Singleton
    fun provideNewsApi(): NewsApiService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(NewsApiService::class.java)
}