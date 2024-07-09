package com.example.data

import androidx.room.TypeConverter
import com.example.domain.model.news.Articles
import com.example.domain.model.sources.Sources
import com.google.gson.Gson

class Converters {

    private val gson = Gson()

    @TypeConverter
    fun sourcesToString(sources: Sources?): String? {
        return gson.toJson(sources)
    }

    @TypeConverter
    fun stringToSources(data: String?): Sources? {
        return gson.fromJson(data, Sources::class.java)
    }

    @TypeConverter
    fun ArtcilesToString(sources: Articles?): String? {
        return gson.toJson(sources)
    }

    @TypeConverter
    fun stringToArticles(data: String?): Articles? {
        return gson.fromJson(data, Articles::class.java)
    }
}
