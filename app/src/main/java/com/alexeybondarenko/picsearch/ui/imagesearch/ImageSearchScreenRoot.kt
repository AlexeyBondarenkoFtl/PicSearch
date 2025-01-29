package com.alexeybondarenko.picsearch.ui.imagesearch

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ImageSearchScreenRoot(
    // todo rework to di
    imageSearchViewModel: ImageSearchViewModel = viewModel()
) {

    val uiState by imageSearchViewModel.uiState.collectAsState(initial = ImageSearchUiState.ImageSearchLoading)

    ImageSearchScreen(
        uiState = uiState,
        onIncreaseClick = imageSearchViewModel::increase
    )

}