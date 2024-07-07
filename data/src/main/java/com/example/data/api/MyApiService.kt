package com.example.data.api

import com.example.data.Constant
import com.example.domain.model.news.NewsResponse
import com.example.domain.model.sources.Sources
import com.example.domain.model.sources.SourcesResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MyApiService {

    @GET("/v2/top-headlines/sources")
    suspend fun getSources(
        @Query("apiKey") key: String = Constant.apiKey,
        @Query("category") category: String
    ): Response<SourcesResponse>


    @GET("/v2/everything")
    suspend fun getNews(
        @Query("apiKey") key: String = Constant.apiKey,
        @Query("Sources") sources: String
    ): Response<NewsResponse>
}
