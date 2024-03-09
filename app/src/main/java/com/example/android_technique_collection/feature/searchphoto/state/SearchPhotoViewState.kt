package com.example.android_technique_collection.feature.searchphoto.state

sealed interface SearchPhotoViewState {
    data object Loading : SearchPhotoViewState
    data class Shown(val photos: List<Photo>) : SearchPhotoViewState
    data class Failure(val error: String) : SearchPhotoViewState
}
