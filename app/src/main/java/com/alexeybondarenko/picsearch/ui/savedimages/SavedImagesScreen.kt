package com.alexeybondarenko.picsearch.ui.savedimages

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.alexeybondarenko.picsearch.ui.utils.common.PicSearchErrorDialog
import com.alexeybondarenko.picsearch.ui.utils.common.PicSearchImageList

@Composable
fun SavedImagesScreen(
    uiState: SavedImagesUiState,
    onImageClick: (id: String) -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        when (uiState) {
            is SavedImagesUiState.SavedImagesLoading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

            is SavedImagesUiState.SavedImagesLoaded -> {
                SavedImagesList(
                    loadedState = uiState,
                    onImageClick = onImageClick,
                )
            }
        }
        uiState.error?.let {
            PicSearchErrorDialog(error = it)
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
            NoSavedImagesLabel()
        } else {
            PicSearchImageList(
                images = loadedState.savedImages,
                onClick = onImageClick,
            )
        }
    }
}

@Composable
private fun BoxScope.NoSavedImagesLabel() {
    Text(
        text = "У тебя нет сохраненных изображений",
        modifier = Modifier.align(Alignment.Center)
    )
}

@Preview(showBackground = true)
@Composable
private fun SavedImagesScreenPreview() {
    SavedImagesScreen(
        uiState = SavedImagesUiState.SavedImagesLoaded(
            savedImages = null,
            error = null,
        ),
        onImageClick = {}
    )
}