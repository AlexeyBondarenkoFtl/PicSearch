package com.alexeybondarenko.data.repository

import android.content.Context
import androidx.core.content.edit
import com.alexeybondarenko.data.repository.utils.SettingsEntityMapper
import com.alexeybondarenko.domain.model.SettingEntity
import com.alexeybondarenko.domain.model.SettingKeyEntity
import com.alexeybondarenko.domain.repository.SettingsService

class SettingsServiceImpl(
    context: Context,
) : SettingsService {
    private val sharedPrefs =
        context.getSharedPreferences(SHARED_PREFERENCES_FILE, Context.MODE_PRIVATE)

    private val mapper = SettingsEntityMapper()

    override fun saveSetting(key: SettingKeyEntity, value: SettingEntity) {
        sharedPrefs.edit {
            putString(key.value, mapper.mapFromEntity(value))
        }
    }

    override fun getSetting(key: SettingKeyEntity, defValue: SettingEntity): SettingEntity {
        val settingValue =
            sharedPrefs.getString(key.value, mapper.mapFromEntity(defValue)) ?: return defValue

        val entity = mapper.mapToEntity(settingValue) ?: return defValue

        return entity
    }

    companion object {
        // do not change file name!
        private const val SHARED_PREFERENCES_FILE = "preference_file_key"
    }
}