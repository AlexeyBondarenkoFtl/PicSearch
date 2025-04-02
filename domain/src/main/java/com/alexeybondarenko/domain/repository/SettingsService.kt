package com.alexeybondarenko.domain.repository

import com.alexeybondarenko.domain.model.ApiSettingEntity
import com.alexeybondarenko.domain.model.LanguageSettingEntity
import com.alexeybondarenko.domain.model.SettingEntity
import com.alexeybondarenko.domain.model.SettingKeyEntity
import com.alexeybondarenko.domain.model.ThemeSettingEntity

interface SettingsService {
    fun saveSetting(key: SettingKeyEntity, value: SettingEntity)

    fun getSetting(key: SettingKeyEntity, defValue: SettingEntity): SettingEntity

    companion object DefaultSettings {
        val apiDefault = ApiSettingEntity.UNSPLASH
        val themeDefault = ThemeSettingEntity.SYSTEM
        val languageDefault = LanguageSettingEntity.RU
    }
}