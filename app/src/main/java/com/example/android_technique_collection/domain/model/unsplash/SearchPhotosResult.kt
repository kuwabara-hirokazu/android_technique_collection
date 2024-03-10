package com.example.android_technique_collection.domain.model.unsplash


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchPhotosResult(
    @Json(name = "results")
    val searchPhotoResults: List<SearchPhotoResult>?,
    val total: Int?,
    @Json(name = "total_pages")
    val totalPages: Int?
)
