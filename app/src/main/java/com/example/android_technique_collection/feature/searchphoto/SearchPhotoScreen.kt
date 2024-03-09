package com.example.android_technique_collection.feature.searchphoto

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.android_technique_collection.feature.searchphoto.component.PhotoThumbnailItem
import com.example.android_technique_collection.feature.searchphoto.state.SearchPhotoViewState
import com.example.android_technique_collection.ui.theme.Android_technique_collectionTheme

@Composable
fun SearchPhotoScreen(
    viewModel: SearchPhotoViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    SearchPhotoScreen(
        uiState
    )
}

@Composable
private fun SearchPhotoScreen(
    uiState: SearchPhotoViewState,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
    ) { padding ->
        when(uiState) {
            is SearchPhotoViewState.Shown -> {
                LazyColumn {
                    items(uiState.photos) { photo ->
                        PhotoThumbnailItem(
                            photo = photo,
                            modifier = Modifier.padding(padding)
                        )
                    }
                }
            }
            is SearchPhotoViewState.Failure -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = uiState.error,
                    )
                }
            }
            SearchPhotoViewState.Loading -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun SearchPhotoScreenPreview() {
    Android_technique_collectionTheme {
        SearchPhotoScreen(
            SearchPhotoViewState.Loading
        )
    }
}
