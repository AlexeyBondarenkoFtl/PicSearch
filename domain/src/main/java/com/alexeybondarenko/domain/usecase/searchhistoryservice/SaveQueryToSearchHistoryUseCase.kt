package com.alexeybondarenko.domain.usecase.searchhistoryservice

import com.alexeybondarenko.domain.model.SearchHistoryEntryEntity
import com.alexeybondarenko.domain.repository.SearchHistoryService

class SaveQueryToSearchHistoryUseCase(
    private val searchHistoryService: SearchHistoryService
) {
    suspend fun execute(searchHistoryEntryEntity: SearchHistoryEntryEntity) {
        searchHistoryService.saveEntry(searchHistoryEntryEntity)
    }
}