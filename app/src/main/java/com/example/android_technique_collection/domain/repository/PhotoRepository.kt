package com.example.android_technique_collection.domain.repository

import com.example.android_technique_collection.domain.model.unsplash.SearchPhotosResult

interface PhotoRepository {

    suspend fun searchPhotos(
        query: String,
        page: Int,
        perPage: Int,
    ): SearchPhotosResult
}
