package com.example.android_technique_collection.data.remote

import com.example.android_technique_collection.domain.model.unsplash.SearchPhotosResult
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface UnsplashApi {

    @Headers("Authorization: Client-ID K3e3TJgOxCTSmHID6INKuyCiq9S12HRtvW4Vr63UJ70")
    @GET("search/photos")
    suspend fun searchPhotos(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
    ): SearchPhotosResult
}
