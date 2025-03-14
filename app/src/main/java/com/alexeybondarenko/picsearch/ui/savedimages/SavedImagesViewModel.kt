package com.alexeybondarenko.picsearch.ui.savedimages

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SavedImagesViewModel(

) : ViewModel() {
    private val viewModelState = MutableStateFlow(SavedImagesViewModelState())

    val uiState = viewModelState
        .map(SavedImagesViewModelState::toUiState)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = viewModelState.value.toUiState()
        )

    private val limit = 20
    private var isLoading = false
    private var isMaximumReached = false
    private val images: ArrayList<String> = arrayListOf()

    init {
        loadFirstSavedImages()
    }

    private fun loadFirstSavedImages() {
        val firstImages = listOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "10")

        images += firstImages

        viewModelState.update {
            it.copy(images = images)
        }
    }

    fun loadNextSavedImages() {
        if (isLoading || isMaximumReached) return
        isLoading = true

        viewModelScope.launch {
            //todo add try catch

            val newImages = listOf("11", "12", "13", "14", "15")

            images += newImages

            if (images.size < limit) {
                isMaximumReached = true
            }

            viewModelState.update {
                it.copy(images = images)
            }

        }
    }

    companion object {
        private const val TAG = "SavedImagesViewModel"
    }
}