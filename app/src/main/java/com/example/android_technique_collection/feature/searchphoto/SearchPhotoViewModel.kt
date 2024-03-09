package com.example.android_technique_collection.feature.searchphoto

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
        MutableStateFlow(SearchPhotoViewState.Loading("Android"))
    val uiState = this._uiState.asStateFlow()

    init {
        searchPhotos()
    }

    fun updateQuery(newQuery: String) {
        when (val state = _uiState.value) {
            is SearchPhotoViewState.Failure -> _uiState.value = state.copy(query = newQuery)
            is SearchPhotoViewState.Loading -> _uiState.value = state.copy(query = newQuery)
            is SearchPhotoViewState.Shown -> _uiState.value = state.copy(query = newQuery)
        }
    }

    fun searchPhotos() {
        val query = _uiState.value.query
        _uiState.value = SearchPhotoViewState.Loading(query)
        viewModelScope.launch {
            try {
                val photos = repository.searchPhotos(query).results?.map {
                    Photo.from(it)
                } ?: emptyList()
                _uiState.value = SearchPhotoViewState.Shown(query = query, photos = photos)
            } catch (e: Exception) {
                _uiState.value =
                    SearchPhotoViewState.Failure(query = query, error = e.message.toString())
            }
        }
    }
}
