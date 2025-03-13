package com.alexeybondarenko.picsearch.ui.imagesearch

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexeybondarenko.domain.model.SearchResultsEntity
import com.alexeybondarenko.domain.usecase.GetPhotosByQueryUseCase
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
        setToOne()
    }

    private fun setToOne() {
        viewModelState.update {
            it.copy(isLoading = true)
        }

        viewModelState.update {
            it.copy(counter = 1)
        }

        viewModelState.update {
            it.copy(isLoading = false)
        }
    }

    fun increase() {
        viewModelState.update { current ->
            current.copy(counter = (current.counter ?: 0) + 1)
        }
    }

    fun getPhotosByQuery() {
        viewModelScope.launch {
            try {
                val query = "coffee"

                val photos: SearchResultsEntity = getPhotosByQueryUseCase.execute(query)
                val smallPhotoUrl = photos.results?.first()?.urls?.small
                Log.d("ImageSearchViewModel", smallPhotoUrl?:"")

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}