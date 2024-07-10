package com.example.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.model.sources.Sources

@Entity
data class SourceEntity(
    @PrimaryKey val id: String,
    val country: String? = null,
    val name: String? = null,
    val description: String? = null,
    val language: String? = null,
    val category: String? = null,
    val url: String? = null
) {
    companion object {
        fun fromSources(source: Sources): SourceEntity {
            return SourceEntity(
                id = source.id ?: "",
                country = source.country,
                name = source.name,
                description = source.description,
                language = source.language,
                category = source.category,
                url = source.url
            )
        }

        fun toSources(entity: SourceEntity): Sources {
            return Sources(
                id = entity.id,
                country = entity.country,
                name = entity.name,
                description = entity.description,
                language = entity.language,
                category = entity.category,
                url = entity.url

            )
        }
    }
}
