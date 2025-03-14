package com.alexeybondarenko.picsearch.ui.savedimages

data class SavedImagesViewModelState(
    val errorMessages: List<String> = emptyList(),
    val isLoading: Boolean = false,
    val images: List<String>? = null
) {
    fun toUiState(): SavedImagesUiState = when {
        isLoading -> SavedImagesUiState.SavedImagesLoading

        images == null -> SavedImagesUiState.SavedImagesLoadingError(
            errorMessages = errorMessages
        )

        images.isEmpty() -> SavedImagesUiState.SavedImagesLoadedEmpty

        else -> SavedImagesUiState.SavedImagesLoaded(
            images = images
        )
    }
}

sealed interface SavedImagesUiState {
    data object SavedImagesLoading : SavedImagesUiState

    data class SavedImagesLoaded(
        val images: List<String>,
    ) : SavedImagesUiState

    data object SavedImagesLoadedEmpty : SavedImagesUiState

    data class SavedImagesLoadingError(
        val errorMessages: List<String> = emptyList(),
    ) : SavedImagesUiState
}
