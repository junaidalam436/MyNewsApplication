package com.example.mynewsapplication.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mynewsapplication.model.Article

/**
 * [NewsDao]
 * Dao are responsible for defining the methods that access the database
 */
@Dao
interface NewsDao {
    @Query("SELECT * FROM articles")
    fun getAllArticles(): List<Article>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(articles: List<Article>)
}