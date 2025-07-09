package com.alexeybondarenko.picsearch.ui.imagesearch.searchhistory

import androidx.lifecycle.viewModelScope
import com.alexeybondarenko.domain.service.searchhistory.usecase.GetAllSearchHistoryEntriesUseCase
import com.alexeybondarenko.picsearch.ui.imagesearch.searchhistory.data.SearchHistoryItem
import com.alexeybondarenko.picsearch.ui.utils.base.viewmodel.PicSearchBaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchHistoryViewModel(
    private val getAllSearchHistoryEntriesUseCase: GetAllSearchHistoryEntriesUseCase,
) : PicSearchBaseViewModel() {
    override val tag: String = "SearchHistoryViewModel"

    private val viewModelState = MutableStateFlow<List<SearchHistoryItem>>(emptyList())

    val uiState = viewModelState
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    init {
        updateSearchHistory()
    }

    fun updateSearchHistory() {
        viewModelScope.launch {
            try {
                val searchHistoryEntries = getAllSearchHistoryEntriesUseCase.execute()

                viewModelState.update {
                    searchHistoryEntries.map { SearchHistoryItem(it.query) }
                }

            } catch (e: Exception) {
                handleError(e)
            }
        }
    }
}