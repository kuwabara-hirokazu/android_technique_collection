package com.example.android_technique_collection.feature.chart

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.android_technique_collection.feature.chart.state.ChartState
import com.example.android_technique_collection.ui.common.theme.Android_technique_collectionTheme

@Composable
fun ChartScreen() {
    ChartScreen(
        uiState = ChartState("")
    )
}

@Composable
private fun ChartScreen(
    uiState: ChartState,
    modifier: Modifier = Modifier
) {
    Scaffold() { padding ->
        Column(
            modifier = modifier.padding(padding)
        ) {
            Text(uiState.value)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ChartScreenPreview() {
    Android_technique_collectionTheme {
        ChartScreen(
            uiState = ChartState("")
        )
    }
}
