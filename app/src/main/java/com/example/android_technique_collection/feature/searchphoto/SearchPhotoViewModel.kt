package com.example.android_technique_collection.feature.searchphoto

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_technique_collection.domain.repository.PhotoRepository
import com.example.android_technique_collection.feature.searchphoto.state.Photo
import com.example.android_technique_collection.feature.searchphoto.state.SearchPhotoViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class SearchPhotoViewModel @Inject constructor(
    private val repository: PhotoRepository,
) : ViewModel() {

    private val _uiState: MutableStateFlow<SearchPhotoViewState> =
        MutableStateFlow(SearchPhotoViewState.Loading)
    val uiState = this._uiState.asStateFlow()

    private var _query by mutableStateOf("Android")
    val query: String
        get() = _query


    init {
        searchPhotos()
    }

    fun updateQuery(newQuery: String) {
        _query = newQuery
    }

    fun searchPhotos() {
        _uiState.value = SearchPhotoViewState.Loading
        viewModelScope.launch {
            try {
                val photos = repository.searchPhotos(query).results?.map {
                    Photo.from(it)
                } ?: emptyList()
                _uiState.value = SearchPhotoViewState.Shown(photos = photos)
            } catch (e: Exception) {
                _uiState.value = SearchPhotoViewState.Failure(error = e.message.toString())
            }
        }
    }
}
