package com.alexeybondarenko.picsearch.ui.imagesearch

import androidx.lifecycle.viewModelScope
import com.alexeybondarenko.domain.models.ImageEntity
import com.alexeybondarenko.domain.models.SearchHistoryEntryEntity
import com.alexeybondarenko.domain.service.imagestorage.usecase.SaveImageToStorageUseCase
import com.alexeybondarenko.domain.service.photo.usecase.GetPhotoByIdUseCase
import com.alexeybondarenko.domain.service.photo.usecase.GetPhotosByQueryUseCase
import com.alexeybondarenko.domain.service.searchhistory.usecase.SaveQueryToSearchHistoryUseCase
import com.alexeybondarenko.picsearch.ui.imagesearch.data.ImageCard
import com.alexeybondarenko.picsearch.ui.utils.ImageUtils.calculateAspectRatio
import com.alexeybondarenko.picsearch.ui.utils.base.viewmodel.PicSearchViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ImageSearchViewModel(
    private val getPhotosByQueryUseCase: GetPhotosByQueryUseCase,
    private val saveImageToStorageUseCase: SaveImageToStorageUseCase,
    private val getPhotoByIdUseCase: GetPhotoByIdUseCase,
    private val saveQueryToSearchHistoryUseCase: SaveQueryToSearchHistoryUseCase,
) : PicSearchViewModel<ImageSearchViewModelState, ImageSearchUiState>(
    initialState = ImageSearchViewModelState()
) {
    override val tag: String = "ImageSearchViewModel"

    private var lastPage = 1

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

                saveQueryToSearchHistoryUseCase.execute(
                    searchHistoryEntryEntity = SearchHistoryEntryEntity(query)
                )

            } catch (e: Exception) {
                handleError(e)
            } finally {
                viewModelState.update {
                    it.copy(isLoading = false)
                }
            }
        }
    }

    // todo move method to detail image screen viewModel
    fun saveImage(id: String) {
        viewModelScope.launch {
            try {
                val image: ImageEntity? = getPhotoByIdUseCase.execute(id)

                image?.let { saveImageToStorageUseCase.execute(it) }
            } catch (e: Exception) {
                handleError(e)
            }
        }
    }

    fun loadNextImages(
        query: String,
    ) {
        lastPage++
        searchByQuery(query)
    }

    fun resetLastPage() {
        lastPage = 1
    }

    private suspend fun searchByQueryInternal(
        query: String,
    ): List<ImageCard>? {
        val photos: List<ImageEntity>? = getPhotosByQueryUseCase.execute(query, lastPage)

        return photos?.map { mapImageEntityToImageCard(it) }
    }

    private fun mapImageEntityToImageCard(from: ImageEntity): ImageCard {
        return ImageCard(
            id = from.id,
            url = from.urls?.regular,
            aspectRatio = calculateAspectRatio(
                width = from.width,
                height = from.height,
            )
        )
    }



}