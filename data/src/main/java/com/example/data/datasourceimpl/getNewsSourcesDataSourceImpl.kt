package com.example.data.datasourceimpl

import com.example.data.api.MyApiService
import com.example.data.datasource.getNewsSourcesDataSource
import com.example.domain.model.news.Articles
import com.example.domain.model.sources.Sources
import javax.inject.Inject

class getNewsSourcesDataSourceImpl @Inject constructor(
    val myApiService: MyApiService
) : getNewsSourcesDataSource {
    override suspend fun getSourcesDataSource(source: String): List<Sources?> {
        val sourceResponse = myApiService.getSources(category = source)
        return if (sourceResponse.isSuccessful) {
            sourceResponse.body()?.sources ?: listOf()
        } else {
            listOf()
        }
    }

    override suspend fun getNewsDataSource(source: String): List<Articles?> {
        val newsResponse = myApiService.getNews(sources = source)
        return if (newsResponse.isSuccessful) {
            newsResponse.body()?.articles ?: listOf()
        } else {
            listOf()
        }
    }
}