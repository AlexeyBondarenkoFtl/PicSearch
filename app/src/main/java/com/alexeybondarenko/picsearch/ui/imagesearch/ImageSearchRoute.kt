package com.alexeybondarenko.picsearch.ui.imagesearch

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel

@Composable
fun ImageSearchRoute(
    viewModel: ImageSearchViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val searchHistoryUiState by viewModel.searchHistoryUiState.collectAsStateWithLifecycle()

    ImageSearchScreen(
        uiState = uiState,
        searchHistory = searchHistoryUiState,
        onSearchClick = viewModel::searchByQuery,
        onImageClick = viewModel::saveImage,
        onRequestNextItems = viewModel::loadNextImages
    )
}