package com.example.android_technique_collection.feature.searchphoto.section

import androidx.annotation.StringRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.android_technique_collection.R
import com.example.android_technique_collection.feature.searchphoto.component.PhotoThumbnailItem
import com.example.android_technique_collection.feature.searchphoto.state.SearchPhotoViewState
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SearchPhotoResultSection(
    uiState: SearchPhotoViewState.Shown,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        val pagerState = rememberPagerState(
            initialPage = 0,
            pageCount = { PhotoResultDisplayType.entries.size }
        )
        val coroutineScope = rememberCoroutineScope()

        TabRow(
            selectedTabIndex = pagerState.currentPage,
            modifier = Modifier.fillMaxWidth()
        ) {
            PhotoResultDisplayType.entries.forEachIndexed { index, displayType ->
                Tab(
                    selected = pagerState.currentPage == index,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    modifier = Modifier.height(40.dp)
                ) {
                    Text(text = stringResource(id = displayType.displayNameResource))
                }
            }
        }

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxHeight()
        ) { page: Int ->
            when (page) {
                PhotoResultDisplayType.LIST.ordinal -> {
                    LazyColumn {
                        items(
                            items = uiState.photos,
                            key = { photo -> photo.photoId }
                        ) { photo ->
                            PhotoThumbnailItem(
                                photo = photo,
                            )
                        }
                    }
                }

                PhotoResultDisplayType.GRID.ordinal -> {
                    LazyVerticalStaggeredGrid(
                        columns = StaggeredGridCells.Fixed(2),
                        content = {
                            items(
                                items = uiState.photos,
                                key = { photo -> photo.photoId }
                            ) { photo ->
                                AsyncImage(
                                    model = photo.imageUrl,
                                    contentScale = ContentScale.Crop,
                                    contentDescription = photo.description,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .wrapContentHeight()
                                )
                            }
                        },
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}

private enum class PhotoResultDisplayType(@StringRes val displayNameResource: Int) {
    LIST(R.string.photo_result_display_type_list),
    GRID(R.string.photo_result_display_type_grid);
}


