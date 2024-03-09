package com.example.android_technique_collection.domain.model.unsplash


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LinksX(
    val html: String?,
    val likes: String?,
    val photos: String?,
    val self: String?
)
