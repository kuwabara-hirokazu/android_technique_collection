package com.example.android_technique_collection.domain.repository

import com.example.android_technique_collection.domain.model.unsplash.SearchPhotoResult

interface PhotoRepository {

    suspend fun searchPhotos(query: String): SearchPhotoResult
}
