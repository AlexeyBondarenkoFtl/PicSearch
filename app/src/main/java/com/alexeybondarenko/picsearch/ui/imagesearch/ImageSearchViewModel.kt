package com.alexeybondarenko.picsearch.ui.imagesearch

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexeybondarenko.domain.model.SearchResultsEntity
import com.alexeybondarenko.domain.usecase.GetPhotosByQueryUseCase
import com.alexeybondarenko.picsearch.ui.utils.common.PicSearchErrorWithAction
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ImageSearchViewModel(
    private val getPhotosByQueryUseCase: GetPhotosByQueryUseCase,
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

    private suspend fun searchByQueryInternal(
        query: String,
    ): List<String>? {
        val photos: SearchResultsEntity = getPhotosByQueryUseCase.execute(query)
        return photos.results?.mapNotNull { it.urls?.small }
    }

    companion object {
        const val TAG = "ImageSearchViewModel"
    }
}