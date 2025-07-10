package com.alexeybondarenko.domain.service.settings.usecase

import com.alexeybondarenko.domain.models.SettingKeyEntity
import com.alexeybondarenko.domain.models.ThemeSettingEntity
import com.alexeybondarenko.domain.service.settings.repository.SettingsService

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