package com.alexeybondarenko.domain.service.searchhistory.repository

import com.alexeybondarenko.domain.models.SearchHistoryEntryEntity

interface SearchHistoryService {
    suspend fun saveEntry(entry: SearchHistoryEntryEntity)

    suspend fun getAllEntries(): List<SearchHistoryEntryEntity>
}