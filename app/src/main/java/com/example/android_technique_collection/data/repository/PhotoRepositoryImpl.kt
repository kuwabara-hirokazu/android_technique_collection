package com.example.android_technique_collection.data.repository

import com.example.android_technique_collection.data.remote.UnsplashApi
import com.example.android_technique_collection.domain.model.unsplash.SearchPhotosResult
import com.example.android_technique_collection.domain.repository.PhotoRepository
import javax.inject.Inject

class PhotoRepositoryImpl @Inject constructor(
    private val unsplashApi: UnsplashApi
) : PhotoRepository {
    override suspend fun searchPhotos(
        query: String,
        page: Int,
        perPage: Int,
    ): SearchPhotosResult {
        return unsplashApi.searchPhotos(query, page, perPage)
    }
}
