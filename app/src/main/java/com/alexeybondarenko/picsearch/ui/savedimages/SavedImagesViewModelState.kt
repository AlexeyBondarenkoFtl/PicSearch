package com.alexeybondarenko.picsearch.ui.savedimages

import com.alexeybondarenko.picsearch.ui.imagesearch.data.ImageCard
import com.alexeybondarenko.picsearch.ui.utils.common.PicSearchErrorWithAction

data class SavedImagesViewModelState(
    val errorMessage: PicSearchErrorWithAction? = null,
    val isLoading: Boolean = false,
    val savedImages: List<ImageCard>? = null,
    val operationErrorMessage: PicSearchErrorWithAction? = null,
) {
    fun toUiState(): SavedImagesUiState = when {
        isLoading -> SavedImagesUiState.SavedImagesLoading

        !isLoading && errorMessage != null -> SavedImagesUiState.SavedImagesLoadingError(
            errorMessage = errorMessage
        )

        else -> SavedImagesUiState.SavedImagesLoaded(
            savedImages = savedImages,
            operationErrorMessage = operationErrorMessage,
        )
    }
}

sealed interface SavedImagesUiState {
    data object SavedImagesLoading : SavedImagesUiState

    data class SavedImagesLoaded(
        val savedImages: List<ImageCard>?,
        val operationErrorMessage: PicSearchErrorWithAction?,
    ) : SavedImagesUiState

    data class SavedImagesLoadingError(
        val errorMessage: PicSearchErrorWithAction?,
    ) : SavedImagesUiState
}
