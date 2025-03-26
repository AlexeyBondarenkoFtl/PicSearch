package com.alexeybondarenko.picsearch.ui.imagesearch

import com.alexeybondarenko.picsearch.ui.utils.common.PicSearchErrorWithAction

data class ImageSearchViewModelState(
    val errorMessage: PicSearchErrorWithAction? = null,
    val isLoading: Boolean = false,
    val searchResults: List<String>? = null,
    val operationErrorMessage: PicSearchErrorWithAction? = null,
) {
    fun toUiState(): ImageSearchUiState = when {
        isLoading -> ImageSearchUiState.ImageSearchLoading

        !isLoading && errorMessage != null -> ImageSearchUiState.ImageSearchLoadingError(
            errorMessage = errorMessage
        )

        else -> ImageSearchUiState.ImageSearchLoaded(
            searchResults = searchResults,
            operationErrorMessage = operationErrorMessage,
        )
    }
}

sealed interface ImageSearchUiState {

    data object ImageSearchLoading : ImageSearchUiState

    data class ImageSearchLoaded(
        val searchResults: List<String>?,
        val operationErrorMessage: PicSearchErrorWithAction?,
    ) : ImageSearchUiState

    data class ImageSearchLoadingError(
        val errorMessage: PicSearchErrorWithAction?,
    ) : ImageSearchUiState
}