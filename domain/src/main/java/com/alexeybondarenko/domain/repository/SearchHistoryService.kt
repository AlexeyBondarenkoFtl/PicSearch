package com.alexeybondarenko.domain.repository

import com.alexeybondarenko.domain.model.SearchHistoryEntryEntity

interface SearchHistoryService {
    suspend fun saveEntry(entry: SearchHistoryEntryEntity)

    suspend fun getAllEntries(): List<SearchHistoryEntryEntity>
}