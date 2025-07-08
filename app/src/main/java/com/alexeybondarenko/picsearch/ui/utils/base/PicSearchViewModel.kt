package com.alexeybondarenko.picsearch.ui.utils.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update


interface PicSearchViewModelState<U> {
    val isLoading: Boolean
    val error: PicSearchError?
    fun toUiState(): U
    fun withError(error: PicSearchError): PicSearchViewModelState<U>
}

abstract class PicSearchViewModel<S : PicSearchViewModelState<U>, U>(
    initialState: S
) : ViewModel() {
    protected val viewModelState = MutableStateFlow(initialState)
    val uiState: StateFlow<U> = viewModelState
        .map { it.toUiState() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = initialState.toUiState()
        )

    fun handleError(e: Exception) {
        e.printStackTrace()
        viewModelState.update { it.withError(PicSearchError(e)) as S }
    }

    abstract val tag: String
}