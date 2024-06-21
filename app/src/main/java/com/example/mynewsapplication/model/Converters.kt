package com.example.mynewsapplication.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromSource(source: Source?): String? {
        return if (source == null) null else Gson().toJson(source)
    }

    @TypeConverter
    fun toSource(sourceString: String?): Source? {
        return if (sourceString == null) null else Gson().fromJson(
            sourceString,
            object : TypeToken<Source>() {}.type
        )
    }
}