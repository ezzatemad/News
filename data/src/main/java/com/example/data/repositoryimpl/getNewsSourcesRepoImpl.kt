package com.example.data.repositoryimpl

import com.example.data.datasource.getNewsSourcesDataSource
import com.example.domain.model.news.Articles
import com.example.domain.model.sources.Sources
import com.example.domain.repository.getNewSourcesRepo
import javax.inject.Inject

class getNewsSourcesRepoImpl @Inject constructor(
    private val getNewsSourcesDataSource: getNewsSourcesDataSource
) : getNewSourcesRepo {
    override suspend fun getSources(source: String): List<Sources?> {
        return getNewsSourcesDataSource.getSourcesDataSource(source)
    }


    override suspend fun getNews(source: String): List<Articles?> {
        return getNewsSourcesDataSource.getNewsDataSource(source)
    }


    override suspend fun getSearchNews(search: String): List<Articles?> {
        return getNewsSourcesDataSource.getSearchNewsDataSource(search)
    }

    override suspend fun getLocalSources(source: String): List<Sources?> {
        return getNewsSourcesDataSource.getLocalSourcesDataSource(source)
    }

    override suspend fun getLocalNews(source: String): List<Articles?> {
        return getNewsSourcesDataSource.getLocalNewsDataSource(source)
    }

    override suspend fun getLocalSearchNews(search: String): List<Articles?> {
        return getNewsSourcesDataSource.getLocalSearchNewsDataSource(search)
    }
}
