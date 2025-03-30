package com.alexeybondarenko.picsearch.ui.imagesearch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexeybondarenko.domain.model.ImageEntity
import com.alexeybondarenko.domain.usecase.imagestorageservice.SaveImageToStorageUseCase
import com.alexeybondarenko.domain.usecase.photoservice.GetPhotosByQueryUseCase
import com.alexeybondarenko.picsearch.ui.imagesearch.data.ImageCard
import com.alexeybondarenko.picsearch.ui.utils.common.PicSearchErrorWithAction
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ImageSearchViewModel(
    private val getPhotosByQueryUseCase: GetPhotosByQueryUseCase,
    private val saveImageToStorageUseCase: SaveImageToStorageUseCase,
) : ViewModel() {
    private val viewModelState = MutableStateFlow(
        ImageSearchViewModelState()
    )

    val uiState = viewModelState
        .map(ImageSearchViewModelState::toUiState)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = viewModelState.value.toUiState()
        )

    init {

    }

    fun searchByQuery(
        query: String,
    ) {
        if (query.isEmpty() or query.isBlank()) return

        viewModelScope.launch {
            try {
                viewModelState.update {
                    it.copy(isLoading = true)
                }

                searchByQueryInternal(query)?.let { urls ->
                    viewModelState.update {
                        it.copy(searchResults = urls)
                    }
                }

            } catch (e: Exception) {
                e.printStackTrace()

                val error = PicSearchErrorWithAction(
                    message = e.message,
                    confirmAction = {
                        viewModelState.update {
                            it.copy(operationErrorMessage = null)
                        }
                    }
                )
                viewModelState.update {
                    it.copy(operationErrorMessage = error)
                }
            } finally {
                viewModelState.update {
                    it.copy(isLoading = false)
                }
            }
        }
    }

    // todo move method to detail image screen viewModel
    fun saveImage(image: ImageEntity) {
        viewModelScope.launch {
            try {
                saveImageToStorageUseCase.execute(image)
            } catch (e: Exception) {
                e.printStackTrace()

                val error = PicSearchErrorWithAction(message = e.message)
                viewModelState.update {
                    it.copy(operationErrorMessage = error)
                }
            }
        }
    }

    private suspend fun searchByQueryInternal(
        query: String,
    ): List<ImageCard>? {
        val photos: List<ImageEntity>? = getPhotosByQueryUseCase.execute(query)

        return photos?.map { resultImageEntity ->
            ImageCard(
                url = resultImageEntity.urls?.regular,
                aspectRatio = calculateAspectRatio(
                    width = resultImageEntity.width,
                    height = resultImageEntity.height,
                )
            )
        }
    }

    private fun calculateAspectRatio(
        width: Int?,
        height: Int?,
    ): Float {
        return when {
            height == null || width == null -> 1f
            height == 0 || width == 0 -> 1f
            else -> width.toFloat() / height.toFloat()
        }
    }

    companion object {
        const val TAG = "ImageSearchViewModel"
    }
}