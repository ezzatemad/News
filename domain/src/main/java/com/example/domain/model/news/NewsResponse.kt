package com.example.domain.model.news

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NewsResponse(

	val totalResults: Int? = null,

	val articles: List<Articles?>? = null,

	val status: String? = null
) : Parcelable