package com.example.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.model.sources.Sources


@Entity
data class SourceEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,

    val country: String? = null,

    val name: String? = null,

    val description: String? = null,

    val language: String? = null,

    val category: String? = null,

    val url: String? = null
) {
    companion object {
        fun fromSources(sources: Sources): SourceEntity {
            return SourceEntity(
                id = sources.id?.toInt(),
                country = sources.country,
                name = sources.name,
                description = sources.description,
                language = sources.language,
                url = sources.url,
                category = sources.category,

                )
        }
    }
}