package com.alexeybondarenko.domain.usecase.searchhistoryservice

import com.alexeybondarenko.domain.model.SearchHistoryEntryEntity
import com.alexeybondarenko.domain.repository.SearchHistoryService

class GetAllSearchHistoryEntriesUseCase(
    private val searchHistoryService: SearchHistoryService
) {
    suspend fun execute(): List<SearchHistoryEntryEntity> {
        return searchHistoryService.getAllEntries()
    }
}