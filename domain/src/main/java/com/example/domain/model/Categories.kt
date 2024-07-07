package com.example.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Categories(
    var apiID: String,
    val drawableResId: Int,
    val titleResID: Int,
    val backgroundColor: Int
) : Parcelable

