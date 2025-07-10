package com.alexeybondarenko.picsearch.ui.imagesearch

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.alexeybondarenko.picsearch.ui.imagesearch.searchhistory.SearchHistoryViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ImageSearchRoute(
    imageSearchViewModel: ImageSearchViewModel = koinViewModel(),
    searchHistoryViewModel: SearchHistoryViewModel = koinViewModel(),
) {
    val imageSearchUiState by imageSearchViewModel.uiState.collectAsStateWithLifecycle()
    val searchHistoryUiState by searchHistoryViewModel.uiState.collectAsStateWithLifecycle()

    ImageSearchScreen(
        uiState = imageSearchUiState,
        searchHistory = searchHistoryUiState,
        onSearchClick = { query ->
            imageSearchViewModel.searchByQuery(query)
            searchHistoryViewModel.updateSearchHistory()
        },
        onImageClick = imageSearchViewModel::saveImage,
        onRequestNextItems = imageSearchViewModel::loadNextImages
    )
}