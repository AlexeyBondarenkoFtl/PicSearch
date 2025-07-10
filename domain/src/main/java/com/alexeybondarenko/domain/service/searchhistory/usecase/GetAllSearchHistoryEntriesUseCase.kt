package com.alexeybondarenko.domain.service.searchhistory.usecase

import com.alexeybondarenko.domain.models.SearchHistoryEntryEntity
import com.alexeybondarenko.domain.service.searchhistory.repository.SearchHistoryService

class GetAllSearchHistoryEntriesUseCase(
    private val searchHistoryService: SearchHistoryService
) {
    suspend fun execute(): List<SearchHistoryEntryEntity> {
        return searchHistoryService.getAllEntries()
    }
}