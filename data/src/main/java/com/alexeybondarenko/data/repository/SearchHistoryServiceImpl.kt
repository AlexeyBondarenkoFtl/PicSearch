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
        val dbModel = mapper.mapFromEntity(entry)

        searchHistoryDao.insertWithLimitCheck(dbModel)
    }

    override suspend fun getAllEntries(): List<SearchHistoryEntryEntity> {
        val entries = searchHistoryDao.getAllValues()

        return entries.map { mapper.mapToEntity(it) }
    }
}