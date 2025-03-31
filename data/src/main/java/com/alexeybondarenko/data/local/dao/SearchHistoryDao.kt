package com.alexeybondarenko.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.alexeybondarenko.data.local.model.SearchHistoryEntryDbModel

@Dao
interface SearchHistoryDao {
    @Insert
    suspend fun insert(entry: SearchHistoryEntryDbModel)

    @Query("SELECT * FROM search_history ORDER BY timestamp DESC")
    suspend fun getAllValues(): List<SearchHistoryEntryDbModel>

    @Query("SELECT COUNT(*) FROM search_history")
    suspend fun getEntriesCount(): Int

    @Query("DELETE FROM search_history WHERE id IN ( " +
            "SELECT id FROM search_history " +
            "ORDER BY timestamp ASC " +
            "LIMIT :entriesToDelete" +
            ")")
    suspend fun deleteOldEntries(entriesToDelete: Int)

    @Transaction
    suspend fun insertWithLimitCheck(newEntry: SearchHistoryEntryDbModel) {
        insert(newEntry)

        val totalEntries = getEntriesCount()
        if (totalEntries > MAX_ENTRIES_LIMIT) {
            deleteOldEntries(totalEntries - MAX_ENTRIES_LIMIT)
        }
    }

    companion object {
        const val MAX_ENTRIES_LIMIT = 5
    }
}