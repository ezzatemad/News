package com.example.data.datasource

import com.example.domain.model.news.Articles
import com.example.domain.model.sources.Sources

interface getNewsSourcesDataSource {

    suspend fun getSourcesDataSource(source: String): List<Sources?>



    suspend fun getNewsDataSource(source: String): List<Articles?>



    suspend fun getSearchNewsDataSource(search: String): List<Articles?>

    suspend fun getLocalSourcesDataSource(source: String): List<Sources?>



    suspend fun getLocalNewsDataSource(source: String): List<Articles?>

    suspend fun getLocalSearchNewsDataSource(search: String): List<Articles?>

}