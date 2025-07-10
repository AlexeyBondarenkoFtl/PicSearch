package com.alexeybondarenko.domain.service.settings.usecase

import com.alexeybondarenko.domain.models.SettingKeyEntity
import com.alexeybondarenko.domain.models.ThemeSettingEntity
import com.alexeybondarenko.domain.service.settings.repository.SettingsService

class SetThemeSettingUseCase(
    private val settingsService: SettingsService,
) {
    fun execute(newThemeSettingValue: ThemeSettingEntity) {
        settingsService.saveSetting(
            key = SettingKeyEntity.THEME,
            value = newThemeSettingValue,
        )
    }
}