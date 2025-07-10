package com.alexeybondarenko.domain.service.settings.repository

import com.alexeybondarenko.domain.models.ApiSettingEntity
import com.alexeybondarenko.domain.models.LanguageSettingEntity
import com.alexeybondarenko.domain.models.SettingEntity
import com.alexeybondarenko.domain.models.SettingKeyEntity
import com.alexeybondarenko.domain.models.ThemeSettingEntity
import kotlinx.coroutines.flow.Flow

interface SettingsService {
    fun saveSetting(key: SettingKeyEntity, value: SettingEntity)

    fun getSetting(key: SettingKeyEntity, defValue: SettingEntity): SettingEntity

    fun observeThemeSetting(): Flow<ThemeSettingEntity>

    companion object DefaultSettings {
        val apiDefault = ApiSettingEntity.UNSPLASH
        val themeDefault = ThemeSettingEntity.SYSTEM
        val languageDefault = LanguageSettingEntity.RU
    }
}