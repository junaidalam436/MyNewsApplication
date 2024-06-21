package com.example.mynewsapplication.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mynewsapplication.model.Article
import com.example.mynewsapplication.model.Converters

/**
 * [NewsDatabase]
 * This class contains the database holder
 */
@Database(entities = [Article::class], version = 1)
@TypeConverters(Converters::class)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao
}