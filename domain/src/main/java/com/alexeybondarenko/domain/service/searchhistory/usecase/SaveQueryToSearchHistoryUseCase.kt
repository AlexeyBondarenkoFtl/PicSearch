package com.alexeybondarenko.domain.service.searchhistory.usecase

import com.alexeybondarenko.domain.models.SearchHistoryEntryEntity
import com.alexeybondarenko.domain.service.searchhistory.repository.SearchHistoryService

class SaveQueryToSearchHistoryUseCase(
    private val searchHistoryService: SearchHistoryService
) {
    suspend fun execute(searchHistoryEntryEntity: SearchHistoryEntryEntity) {
        searchHistoryService.saveEntry(searchHistoryEntryEntity)
    }
}