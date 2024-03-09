package com.example.android_technique_collection.domain.model.unsplash


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchPhotoResult(
    val results: List<Result>?,
    val total: Int?,
    @Json(name = "total_pages")
    val totalPages: Int?
)
