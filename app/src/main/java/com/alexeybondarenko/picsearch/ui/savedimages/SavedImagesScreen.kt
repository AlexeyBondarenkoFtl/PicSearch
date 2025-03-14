package com.alexeybondarenko.picsearch.ui.savedimages

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.alexeybondarenko.picsearch.ui.utils.common.PicSearchImageList
import org.koin.androidx.compose.koinViewModel

@Composable
fun SavedImagesRoute(
    viewModel: SavedImagesViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    SavedImagesScreen(
        uiState = uiState,
        onLastItemReached = viewModel::loadNextSavedImages
    )
}

@Composable
fun SavedImagesScreen(
    uiState: SavedImagesUiState,
    onLastItemReached: () -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        when (uiState) {
            SavedImagesUiState.SavedImagesLoading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

            is SavedImagesUiState.SavedImagesLoadingError -> {
                Text(
                    text = uiState.errorMessages.firstOrNull().toString(),
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            SavedImagesUiState.SavedImagesLoadedEmpty -> {
                Text(
                    text = "У тебя нет сохраненных изображений",
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            is SavedImagesUiState.SavedImagesLoaded -> {
                PicSearchImageList(
                    images = uiState.images,
                    onLastItemReached = onLastItemReached,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SavedImagesScreenPreview() {
    SavedImagesScreen(
        uiState = SavedImagesUiState.SavedImagesLoaded(
            images = listOf("1", "2")
        ),
        onLastItemReached = {}
    )
}