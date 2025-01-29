package com.alexeybondarenko.picsearch.ui.imagesearch

data class ImageSearchViewModelState(
    val errorMessages: List<String> = emptyList(),
    val isLoading: Boolean = false,
    val counter: Int? = null
) {
    fun toUiState(): ImageSearchUiState = if (isLoading) {
        ImageSearchUiState.ImageSearchLoading
    } else if (counter == null) {
        ImageSearchUiState.ImageSearchLoadingError(
            errorMessages = errorMessages
        )
    } else {
        ImageSearchUiState.ImageSearchLoaded(
            counter = counter,
            errorMessages = errorMessages
        )
    }
}

sealed interface ImageSearchUiState {

    data object ImageSearchLoading : ImageSearchUiState

    data class ImageSearchLoaded(
        val counter: Int,
        val errorMessages: List<String> = emptyList(),
    ) : ImageSearchUiState

    data class ImageSearchLoadingError(
        val errorMessages: List<String>,
    ) : ImageSearchUiState
}