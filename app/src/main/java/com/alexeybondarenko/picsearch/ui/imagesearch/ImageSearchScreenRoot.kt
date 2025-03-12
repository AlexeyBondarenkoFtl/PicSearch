package com.alexeybondarenko.picsearch.ui.imagesearch

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import org.koin.androidx.compose.koinViewModel

@Composable
fun ImageSearchScreenRoot(
    imageSearchViewModel: ImageSearchViewModel = koinViewModel()
) {

    val uiState by imageSearchViewModel.uiState.collectAsState(initial = ImageSearchUiState.ImageSearchLoading)

    ImageSearchScreen(
        uiState = uiState,
        onIncreaseClick = imageSearchViewModel::increase,
        onGetFirstPostClick = imageSearchViewModel::getFirstPost,
        onLoadPhotoClick = imageSearchViewModel::getPhotosByQuery,
    )

}