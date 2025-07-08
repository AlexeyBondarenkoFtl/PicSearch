package com.alexeybondarenko.picsearch.ui.savedimages

import androidx.lifecycle.viewModelScope
import com.alexeybondarenko.domain.usecase.imagestorageservice.DeleteImageByIdFromStorageUseCase
import com.alexeybondarenko.domain.usecase.imagestorageservice.GetAllImagesFromStorageUseCase
import com.alexeybondarenko.picsearch.ui.imagesearch.data.ImageCard
import com.alexeybondarenko.picsearch.ui.utils.ImageUtils.calculateAspectRatio
import com.alexeybondarenko.picsearch.ui.utils.base.PicSearchViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SavedImagesViewModel(
    private val getAllImagesFromStorageUseCase: GetAllImagesFromStorageUseCase,
    private val deleteImageByIdFromStorageUseCase: DeleteImageByIdFromStorageUseCase,
) : PicSearchViewModel<SavedImagesViewModelState, SavedImagesUiState>(
    initialState = SavedImagesViewModelState()
) {
    override val tag: String = "SavedImagesViewModel"

    private val limit = 20
    private var isLoading = false
    private var isMaximumReached = false
    private val images: ArrayList<String> = arrayListOf()

    init {
//        loadFirstSavedImages()
        loadSavedImages()
    }

    private fun loadSavedImages() {
        viewModelState.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            try {
                val images = getAllImagesFromStorageUseCase.execute()

                val imageCards = images.map { imageEntity ->
                    ImageCard(
                        id = imageEntity.id,
                        url = imageEntity.urls?.regular,
                        aspectRatio = calculateAspectRatio(
                            width = imageEntity.width,
                            height = imageEntity.height,
                        )
                    )
                }
                viewModelState.update { it.copy(savedImages = imageCards) }

            } catch (e: Exception) {
                handleError(e)
            } finally {
                viewModelState.update { it.copy(isLoading = false) }
            }
        }
    }

    fun deleteImage(id: String) {
        viewModelScope.launch {
            try {
                deleteImageByIdFromStorageUseCase.execute(id)

                val images = getAllImagesFromStorageUseCase.execute()

                val imageCards = images.map { imageEntity ->
                    ImageCard(
                        id = imageEntity.id,
                        url = imageEntity.urls?.regular,
                        aspectRatio = calculateAspectRatio(
                            width = imageEntity.width,
                            height = imageEntity.height,
                        )
                    )
                }
                viewModelState.update { it.copy(savedImages = imageCards) }

            } catch (e: Exception) {
                handleError(e)
            }
        }
    }

//    private fun loadFirstSavedImages() {
//        val firstImages = listOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "10")
//
//        images += firstImages
//
//        viewModelState.update {
//            it.copy(images = images)
//        }
//    }
//
//    fun loadNextSavedImages() {
//        if (isLoading || isMaximumReached) return
//        isLoading = true
//
//        viewModelScope.launch {
//            //todo add try catch
//
//            val newImages = listOf("11", "12", "13", "14", "15")
//
//            images += newImages
//
//            if (images.size < limit) {
//                isMaximumReached = true
//            }
//
//            viewModelState.update {
//                it.copy(images = images)
//            }
//
//        }
//    }
}