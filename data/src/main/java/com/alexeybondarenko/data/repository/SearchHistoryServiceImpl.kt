package com.alexeybondarenko.data.repository

import com.alexeybondarenko.data.local.dao.SearchHistoryDao
import com.alexeybondarenko.data.local.mapper.SearchHistoryEntryDbModelMapper
import com.alexeybondarenko.domain.models.SearchHistoryEntryEntity
import com.alexeybondarenko.domain.service.searchhistory.repository.SearchHistoryService

class SearchHistoryServiceImpl(
    private val searchHistoryDao: SearchHistoryDao,
) : SearchHistoryService {
    private val mapper = SearchHistoryEntryDbModelMapper()

    override suspend fun saveEntry(entry: SearchHistoryEntryEntity) {
        if (checkIsPreviousEntryWasTheSame(entry)) return

        val dbModel = mapper.mapFromEntity(entry)
        searchHistoryDao.insertWithLimitCheck(dbModel)
    }

    override suspend fun getAllEntries(): List<SearchHistoryEntryEntity> {
        val entries = searchHistoryDao.getAllValues()

        return entries.map { mapper.mapToEntity(it) }
    }

    private suspend fun checkIsPreviousEntryWasTheSame(newEntry: SearchHistoryEntryEntity): Boolean {
        return searchHistoryDao
            .getAllValues()
            .firstOrNull()
            ?.let { mapper.mapToEntity(it).query == newEntry.query }
            ?: false
    }
}