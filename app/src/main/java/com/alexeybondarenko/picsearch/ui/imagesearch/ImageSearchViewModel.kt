package com.alexeybondarenko.picsearch.ui.imagesearch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexeybondarenko.domain.repository.SecondRepository
import com.alexeybondarenko.domain.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class ImageSearchViewModel(
    private val userRepository: UserRepository,
    private val secondRepository: SecondRepository,
) : ViewModel() {
    private val viewModelState = MutableStateFlow(
        ImageSearchViewModelState()
    )

    val uiState = viewModelState
        .map(ImageSearchViewModelState::toUiState)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = viewModelState.value.toUiState()
        )

    init {
        setToOne()
    }

    fun setToOne() {
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
        viewModelState.value.counter?.let { current ->
            viewModelState.update {
                it.copy(counter = current + 1)
            }
        }
    }
}