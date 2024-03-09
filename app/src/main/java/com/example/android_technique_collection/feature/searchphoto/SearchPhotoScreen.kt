package com.example.android_technique_collection.feature.searchphoto

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
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
        Text(
            text = "Hello ${uiState.name}",
            modifier = Modifier.padding(padding)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun SearchPhotoScreenPreview() {
    Android_technique_collectionTheme {
        SearchPhotoScreen(
            SearchPhotoViewState(name = "Android")
        )
    }
}
