package com.alexeybondarenko.domain.repository

import com.alexeybondarenko.domain.model.SettingEntity
import com.alexeybondarenko.domain.model.SettingKeyEntity

interface SettingsService {
    fun saveSetting(key: SettingKeyEntity, value: SettingEntity)

    fun getSetting(key: SettingKeyEntity, defValue: SettingEntity): SettingEntity?
}