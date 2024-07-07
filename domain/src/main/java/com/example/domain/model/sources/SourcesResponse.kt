package com.example.domain.model.sources

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable


@Parcelize
data class SourcesResponse(

    val sources: List<Sources?>? = null,

    val status: String? = null
) : Parcelable
