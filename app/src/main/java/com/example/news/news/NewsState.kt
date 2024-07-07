package com.example.news.news

import com.example.domain.model.news.Articles
import com.example.domain.model.sources.Sources

sealed class NewsState {

    data object Loading : NewsState()
    data class Success(val sources: List<Sources?>) : NewsState()
    data class LoadNews(val news: List<Articles?>) : NewsState()
    data class Error(val exception: String) : NewsState()

}
