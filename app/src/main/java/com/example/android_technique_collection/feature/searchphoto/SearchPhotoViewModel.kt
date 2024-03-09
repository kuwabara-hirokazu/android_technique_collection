package com.example.android_technique_collection.feature.searchphoto

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_technique_collection.buildUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

@HiltViewModel
class SearchPhotoViewModel @Inject constructor(
) : ViewModel() {

    private val searchPhoto: StateFlow<String> = flow<String> { "ToDo" }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = "initialValue",
        )

    val uiState: StateFlow<SearchPhotoViewState> = buildUiState(searchPhoto) {
        SearchPhotoViewState(
            name = it
        )
    }
}
