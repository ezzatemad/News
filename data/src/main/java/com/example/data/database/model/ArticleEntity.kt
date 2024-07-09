package com.example.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.data.Converters
import com.example.domain.model.news.Articles
import com.example.domain.model.sources.Sources

@Entity(tableName = "Articles")
@TypeConverters(Converters::class)
data class ArticleEntity(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val publishedAt: String? = null,
    val author: String? = null,
    val urlToImage: String? = null,
    val description: String? = null,
    val source: Sources? = null,
    val title: String? = null,
    val url: String? = null,
    val content: String? = null
) {
    companion object {
        fun fromArticles(article: Articles): ArticleEntity {
            return ArticleEntity(
                publishedAt = article.publishedAt,
                author = article.author,
                urlToImage = article.urlToImage,
                description = article.description,
                source = article.source,
                title = article.title,
                url = article.url,
                content = article.content
            )
        }
    }
}
