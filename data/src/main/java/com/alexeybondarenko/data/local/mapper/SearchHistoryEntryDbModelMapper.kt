package com.alexeybondarenko.data.local.mapper

import com.alexeybondarenko.data.local.model.SearchHistoryEntryDbModel
import com.alexeybondarenko.domain.model.SearchHistoryEntryEntity
import com.alexeybondarenko.domain.utils.Mapper

class SearchHistoryEntryDbModelMapper :
    Mapper<SearchHistoryEntryDbModel, SearchHistoryEntryEntity>() {
    override fun mapToEntity(from: SearchHistoryEntryDbModel): SearchHistoryEntryEntity {
        return SearchHistoryEntryEntity(
            query = from.query,
        )
    }

    override fun mapFromEntity(from: SearchHistoryEntryEntity): SearchHistoryEntryDbModel {
        return SearchHistoryEntryDbModel(
            query = from.query,
        )
    }
}