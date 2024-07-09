package com.example.domain.repository

import com.example.domain.model.news.Articles
import com.example.domain.model.sources.Sources

interface getNewSourcesRepo {

    suspend fun getSources(source: String): List<Sources?>

    suspend fun getNews(source: String): List<Articles?>


    suspend fun getSearchNews(search: String): List<Articles?>
}