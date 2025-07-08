package com.alexeybondarenko.picsearch.ui.imagesearch

import com.alexeybondarenko.picsearch.ui.imagesearch.data.ImageCard
import com.alexeybondarenko.picsearch.ui.utils.base.PicSearchError
import com.alexeybondarenko.picsearch.ui.utils.base.PicSearchViewModelState

data class ImageSearchViewModelState(
    override val error: PicSearchError? = null,
    override val isLoading: Boolean = false,
    val searchResults: List<ImageCard>? = null,
) : PicSearchViewModelState<ImageSearchUiState> {
    override fun toUiState(): ImageSearchUiState = when {
        isLoading -> ImageSearchUiState.ImageSearchLoading(
            error = error,
        )

        else -> ImageSearchUiState.ImageSearchLoaded(
            searchResults = searchResults,
            error = error,
        )
    }

    override fun withError(error: PicSearchError): PicSearchViewModelState<ImageSearchUiState> {
        return this.copy(error = error)
    }
}

sealed interface ImageSearchUiState {
    val error: PicSearchError?

    data class ImageSearchLoading(
        override val error: PicSearchError?
    ) : ImageSearchUiState

    data class ImageSearchLoaded(
        val searchResults: List<ImageCard>?,
        override val error: PicSearchError?,
    ) : ImageSearchUiState
}