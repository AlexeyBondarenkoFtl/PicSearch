package com.alexeybondarenko.picsearch.ui.savedimages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel

@Composable
fun SavedImagesRoute(
    viewModel: SavedImagesViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    SavedImagesScreen(
        uiState = uiState,
        onImageClick = viewModel::deleteImage
    )
}