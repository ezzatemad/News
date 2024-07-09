package com.example.domain.model.news

import android.os.Parcelable
import com.example.domain.model.sources.Sources
import kotlinx.parcelize.Parcelize


@Parcelize
data class Articles(


    val id: Int? = null,

    val publishedAt: String? = null,

    val author: String? = null,

    val urlToImage: String? = null,

    val description: String? = null,

    val source: Sources? = null,

    val title: String? = null,

    val url: String? = null,

    val content: String? = null
) : Parcelable