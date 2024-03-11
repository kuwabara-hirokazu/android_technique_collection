package com.example.android_technique_collection.feature.searchphoto.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.android_technique_collection.feature.searchphoto.state.PagingState
import com.example.android_technique_collection.feature.searchphoto.state.Photo

@Composable
fun PhotoList(
    photos: List<Photo>,
    pagingState: PagingState,
    onReachedToLastItem: () -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(
            items = photos,
            key = { photo -> photo.photoId }
        ) { photo ->
            PhotoThumbnailItem(
                photo = photo,
            )
        }
        item {
            when (pagingState) {
                PagingState.FULL -> {}

                // PagingState.READYの時点で予めインジケーターを追加しておくことで、リスト末尾に到達した際に表示されているようにする。
                // リスト末尾にアイテムを追加した際にインジケーターが画面外に配置されてしまう問題を回避するため。
                PagingState.READY, PagingState.LOADING -> {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        CircularProgressIndicator()
                    }
                }

                PagingState.ERROR -> {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(text = "読み込みに失敗しました")
                    }
                }
            }
        }
        item(key = true) {
            LaunchedEffect(key1 = true) {
                onReachedToLastItem.invoke()
            }
        }
    }
}
