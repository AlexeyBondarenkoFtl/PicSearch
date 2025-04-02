package com.alexeybondarenko.domain.usecase.settingsservice

import com.alexeybondarenko.domain.model.SettingKeyEntity
import com.alexeybondarenko.domain.model.ThemeSettingEntity
import com.alexeybondarenko.domain.repository.SettingsService

class GetThemeSettingUseCase(
    private val settingsService: SettingsService,
) {
    private val defValue = SettingsService.themeDefault

    fun execute(): ThemeSettingEntity {
        val setting = settingsService.getSetting(
            key = SettingKeyEntity.THEME,
            defValue = ThemeSettingEntity.SYSTEM,
        )

        return setting as? ThemeSettingEntity ?: defValue
    }
}