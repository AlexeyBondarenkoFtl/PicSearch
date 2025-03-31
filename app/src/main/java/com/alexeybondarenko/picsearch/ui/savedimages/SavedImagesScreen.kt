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
import com.alexeybondarenko.picsearch.ui.utils.common.PicSearchAlertDialog
import com.alexeybondarenko.picsearch.ui.utils.common.PicSearchImageList
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

@Composable
fun SavedImagesScreen(
    uiState: SavedImagesUiState,
    onImageClick: (id: String) -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        when (uiState) {
            SavedImagesUiState.SavedImagesLoading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

            is SavedImagesUiState.SavedImagesLoadingError -> {
                uiState.errorMessage?.let { error ->
                    PicSearchAlertDialog(error = error)
                }
            }

            is SavedImagesUiState.SavedImagesLoaded -> {
                SavedImagesList(
                    loadedState = uiState,
                    onImageClick = onImageClick,
                )
            }
        }
    }
}

@Composable
private fun SavedImagesList(
    modifier: Modifier = Modifier,
    loadedState: SavedImagesUiState.SavedImagesLoaded,
    onImageClick: (id: String) -> Unit,
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        if (loadedState.savedImages.isNullOrEmpty()) {
            Text(
                text = "У тебя нет сохраненных изображений",
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            PicSearchImageList(
                images = loadedState.savedImages,
                onClick = onImageClick,
            )
        }

        loadedState.operationErrorMessage?.let { error ->
            PicSearchAlertDialog(error = error)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SavedImagesScreenPreview() {
    SavedImagesScreen(
        uiState = SavedImagesUiState.SavedImagesLoaded(
            savedImages = null,
            operationErrorMessage = null
        ),
        onImageClick = {}
    )
}