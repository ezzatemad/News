package com.example.news.news

sealed class NewsIntent {

    data class LoadSource(val source: String) : NewsIntent()

    data class LoadNews(val source: String) : NewsIntent()
}
