package com.example.data



import com.example.domain.model.sources.Sources

fun Sources.toDomain(): Sources {
    return Sources(
        id = id,
        name = name,
        description = description,
        url = url,
        category = category,
        language = language,
        country = country
    )
}
