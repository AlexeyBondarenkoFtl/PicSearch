package com.alexeybondarenko.picsearch.ui.savedimages

import com.alexeybondarenko.picsearch.ui.imagesearch.data.ImageCard
import com.alexeybondarenko.picsearch.ui.utils.base.PicSearchError
import com.alexeybondarenko.picsearch.ui.utils.base.PicSearchViewModelState

data class SavedImagesViewModelState(
    override val error: PicSearchError? = null,
    override val isLoading: Boolean = false,
    val savedImages: List<ImageCard>? = null,
) : PicSearchViewModelState<SavedImagesUiState> {
    override fun toUiState(): SavedImagesUiState = when {
        isLoading -> SavedImagesUiState.SavedImagesLoading(
            error = error,
        )

        else -> SavedImagesUiState.SavedImagesLoaded(
            savedImages = savedImages,
            error = error,
        )
    }

    override fun withError(error: PicSearchError): PicSearchViewModelState<SavedImagesUiState> {
        return this.copy(error = error)
    }
}

sealed interface SavedImagesUiState {
    val error: PicSearchError?

    data class SavedImagesLoading(
        override val error: PicSearchError?,
    ) : SavedImagesUiState

    data class SavedImagesLoaded(
        val savedImages: List<ImageCard>?,
        override val error: PicSearchError?,
    ) : SavedImagesUiState
}