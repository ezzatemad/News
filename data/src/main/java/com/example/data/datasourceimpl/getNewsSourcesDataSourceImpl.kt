package com.example.data.datasourceimpl

import android.util.Log
import com.example.data.api.MyApiService
import com.example.data.database.dao.ArticlesDao
import com.example.data.database.dao.SourcesDao
import com.example.data.database.model.ArticleEntity
import com.example.data.database.model.SourceEntity
import com.example.data.datasource.getNewsSourcesDataSource
import com.example.domain.model.news.Articles
import com.example.domain.model.sources.Sources
import javax.inject.Inject

class getNewsSourcesDataSourceImpl @Inject constructor(
    val myApiService: MyApiService,
    private val sourceDao: SourcesDao,
    private val articlesDao: ArticlesDao
) : getNewsSourcesDataSource {
    override suspend fun getSourcesDataSource(source: String): List<Sources?> {
        val sourceResponse = myApiService.getSources(category = source)
        return if (sourceResponse.isSuccessful) {
            val sources = sourceResponse.body()?.sources ?: listOf()

            Log.d("TAG", "getNewsDataSource:ds $sources")

//            // Convert Sources to SourceEntity and save to Room database
//            if (sources.isNotEmpty()) {
//                val sourceEntities = sources.mapNotNull {
//                    it?.let { SourceEntity.fromSources(it) }
//                }
//                sourceDao.insertAll(sourceEntities)
//            }
//
            sources
        } else {
            listOf()
        }
    }

    override suspend fun getNewsDataSource(source: String): List<Articles?> {
        val newsResponse = myApiService.getNews(sources = source)
        return if (newsResponse.isSuccessful) {
            val articles = newsResponse.body()?.articles ?: listOf()
            Log.d("TAG", "getNewsDataSource:ds $articles")

//             Convert Sources to SourceEntity and save to Room database


//            if (articles.isNotEmpty()) {
//                val articlesEntities = articles.mapNotNull {
//                    it?.let { ArticleEntity.fromArticles(it) }
//                }
//                articlesDao.insertAll(articlesEntities)
//            }

            articles
        } else {
            listOf()
        }
    }

    override suspend fun getSearchNewsDataSource(search: String): List<Articles?> {
        val searchResponse = myApiService.getSearchNews(searchIn = search)
        return if (searchResponse.isSuccessful) {
            searchResponse.body()?.articles ?: listOf()
        } else {
            listOf()
        }
    }
}