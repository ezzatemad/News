package com.example.data

import androidx.room.TypeConverter
import com.example.domain.model.sources.Sources
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    @TypeConverter
    fun fromSource(source: Sources?): String? {
        return if (source == null) null else Gson().toJson(source)
    }

    @TypeConverter
    fun toSource(sourceString: String?): Sources? {
        return if (sourceString == null) null else Gson().fromJson(sourceString, object : TypeToken<Sources>() {}.type)
    }
}
