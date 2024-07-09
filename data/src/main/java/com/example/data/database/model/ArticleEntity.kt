package com.example.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.model.news.Articles
import com.example.domain.model.sources.Sources


@Entity(tableName = "Articles")
data class ArticleEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,

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
        fun fromArticles(articles: Articles): ArticleEntity {
            return ArticleEntity(
                publishedAt = articles.publishedAt,
                author = articles.author,
                urlToImage = articles.urlToImage,
                description = articles.description,
                source = articles.source,
                title = articles.title,
                url = articles.url,
                content = articles.content,
            )
        }
    }
}
