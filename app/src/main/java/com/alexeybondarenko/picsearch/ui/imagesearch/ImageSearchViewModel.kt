package com.alexeybondarenko.picsearch.ui.imagesearch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexeybondarenko.domain.model.ImageEntity
import com.alexeybondarenko.domain.model.SearchHistoryEntryEntity
import com.alexeybondarenko.domain.usecase.imagestorageservice.SaveImageToStorageUseCase
import com.alexeybondarenko.domain.usecase.photoservice.GetPhotoByIdUseCase
import com.alexeybondarenko.domain.usecase.photoservice.GetPhotosByQueryUseCase
import com.alexeybondarenko.domain.usecase.searchhistoryservice.GetAllSearchHistoryEntriesUseCase
import com.alexeybondarenko.domain.usecase.searchhistoryservice.SaveQueryToSearchHistoryUseCase
import com.alexeybondarenko.picsearch.ui.imagesearch.data.ImageCard
import com.alexeybondarenko.picsearch.ui.imagesearch.data.SearchHistoryItem
import com.alexeybondarenko.picsearch.ui.utils.ImageUtils.calculateAspectRatio
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
    private val getPhotoByIdUseCase: GetPhotoByIdUseCase,
    private val getAllSearchHistoryEntriesUseCase: GetAllSearchHistoryEntriesUseCase,
    private val saveQueryToSearchHistoryUseCase: SaveQueryToSearchHistoryUseCase,
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

    private val searchHistoryState = MutableStateFlow<List<SearchHistoryItem>>(emptyList())

    val searchHistoryUiState = searchHistoryState
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )


    init {
        updateSearchHistory()
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

                saveQueryToSearchHistoryUseCase.execute(
                    searchHistoryEntryEntity = SearchHistoryEntryEntity(query)
                )

            } catch (e: Exception) {
                handleException(e)
            } finally {
                viewModelState.update {
                    it.copy(isLoading = false)
                }

                updateSearchHistory()
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
                handleException(e)
            }
        }
    }

    private suspend fun searchByQueryInternal(
        query: String,
    ): List<ImageCard>? {
        val photos: List<ImageEntity>? = getPhotosByQueryUseCase.execute(query)

        return photos?.map { imageEntity ->
            ImageCard(
                id = imageEntity.id,
                url = imageEntity.urls?.regular,
                aspectRatio = calculateAspectRatio(
                    width = imageEntity.width,
                    height = imageEntity.height,
                )
            )
        }
    }

    private fun updateSearchHistory() {
        viewModelScope.launch {
            try {
                val searchHistoryEntries = getAllSearchHistoryEntriesUseCase.execute()

                searchHistoryState.update {
                    searchHistoryEntries.map { SearchHistoryItem(it.query) }
                }

            } catch (e: Exception) {
                handleException(e)
            }
        }
    }

    private fun handleException(e: Exception) {
        e.printStackTrace()

        val error = PicSearchErrorWithAction(
            message = e.message,
            confirmAction = {
                viewModelState.update {
                    it.copy(operationErrorMessage = null)
                }
            })
        viewModelState.update {
            it.copy(operationErrorMessage = error)
        }
    }

    companion object {
        const val TAG = "ImageSearchViewModel"
    }
}