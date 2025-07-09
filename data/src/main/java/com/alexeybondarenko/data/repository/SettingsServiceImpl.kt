package com.alexeybondarenko.data.repository

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.alexeybondarenko.data.repository.utils.SettingsEntityMapper
import com.alexeybondarenko.domain.models.SettingEntity
import com.alexeybondarenko.domain.models.SettingKeyEntity
import com.alexeybondarenko.domain.models.ThemeSettingEntity
import com.alexeybondarenko.domain.service.settings.repository.SettingsService
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

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

    override fun observeThemeSetting(): Flow<ThemeSettingEntity> = callbackFlow {
        val listener = SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
            if (key == SettingKeyEntity.THEME.value) {
                val theme = getSetting(SettingKeyEntity.THEME, SettingsService.DefaultSettings.themeDefault) as ThemeSettingEntity
                trySend(theme)
            }
        }
        sharedPrefs.registerOnSharedPreferenceChangeListener(listener)

        val initialTheme = getSetting(SettingKeyEntity.THEME, SettingsService.DefaultSettings.themeDefault) as ThemeSettingEntity
        trySend(initialTheme)

        awaitClose { sharedPrefs.unregisterOnSharedPreferenceChangeListener(listener) }
    }

    companion object {
        // do not change file name!
        private const val SHARED_PREFERENCES_FILE = "preference_file_key"
    }
}