package com.example.android_technique_collection.feature.searchphoto.state

import com.example.android_technique_collection.domain.model.unsplash.Result

data class Photo(
    val photoId: String,
    val description: String?,
    val likes: Int?,
    val imageUrl: String,
    val photographer: String?,
) {
    companion object {
        fun from(searchPhotoResult: Result): Photo {
            if (searchPhotoResult.id == null) throw NullPointerException("photoIdがnullです")
            if (searchPhotoResult.urls?.raw == null) throw NullPointerException("imageUrlがnullです")
            return Photo(
                photoId = searchPhotoResult.id,
                description = searchPhotoResult.description,
                likes = searchPhotoResult.likes,
                imageUrl = searchPhotoResult.urls.raw,
                photographer = searchPhotoResult.user?.username
            )
        }
    }
}
