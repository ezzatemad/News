package com.example.domain.usecase

import com.example.domain.model.news.Articles
import com.example.domain.model.sources.Sources
import com.example.domain.repository.getNewSourcesRepo
import javax.inject.Inject

class getNewsSourcesUseCase @Inject constructor(
    private val newSourcesRepo: getNewSourcesRepo
) {

    suspend fun getSources(source: String): List<Sources?> {
        return newSourcesRepo.getSources(source)
    }


    suspend fun getNews(source: String): List<Articles?> {
        return newSourcesRepo.getNews(source)
    }


    suspend fun getSearchNews(search: String): List<Articles?> {
        return newSourcesRepo.getSearchNews(search)
    }
}