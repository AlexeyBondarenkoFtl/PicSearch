package com.alexeybondarenko.picsearch.ui.imagesearch

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
import com.alexeybondarenko.picsearch.ui.utils.base.PicSearchViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ImageSearchViewModel(
    private val getPhotosByQueryUseCase: GetPhotosByQueryUseCase,
    private val saveImageToStorageUseCase: SaveImageToStorageUseCase,
    private val getPhotoByIdUseCase: GetPhotoByIdUseCase,
    private val getAllSearchHistoryEntriesUseCase: GetAllSearchHistoryEntriesUseCase,
    private val saveQueryToSearchHistoryUseCase: SaveQueryToSearchHistoryUseCase,
) : PicSearchViewModel<ImageSearchViewModelState, ImageSearchUiState>(
    initialState = ImageSearchViewModelState()
) {
    override val tag: String = "ImageSearchViewModel"

    // todo move to separate screen
    private val searchHistoryState = MutableStateFlow<List<SearchHistoryItem>>(emptyList())

    // todo move to separate screen
    val searchHistoryUiState = searchHistoryState
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    private var lastPage = 1

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
                handleError(e)
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

    // todo move to separate screen
    private fun updateSearchHistory() {
        viewModelScope.launch {
            try {
                val searchHistoryEntries = getAllSearchHistoryEntriesUseCase.execute()

                searchHistoryState.update {
                    searchHistoryEntries.map { SearchHistoryItem(it.query) }
                }

            } catch (e: Exception) {
                handleError(e)
            }
        }
    }

}