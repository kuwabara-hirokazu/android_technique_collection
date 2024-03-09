package com.example.android_technique_collection.data.remote

import com.example.android_technique_collection.UNSPLASH_API_KEY
import com.example.android_technique_collection.domain.model.unsplash.SearchPhotoResult
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface UnsplashApi {

    @Headers("Authorization: Client-ID $UNSPLASH_API_KEY")
    @GET("search/photos")
    suspend fun searchPhotos(@Query("query") query: String): SearchPhotoResult
}
