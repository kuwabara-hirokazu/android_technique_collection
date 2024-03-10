package com.example.android_technique_collection.feature.searchphoto.state

sealed interface SearchPhotoViewState {

    val query: String

    data class Loading(
        override val query: String
    ) : SearchPhotoViewState

    data class Shown(
        override val query: String,
        val photos: List<Photo>
    ) : SearchPhotoViewState

    data class NoResult(
        override val query: String
    ) : SearchPhotoViewState

    data class Failure(
        override val query: String,
        val error: String
    ) : SearchPhotoViewState
}
