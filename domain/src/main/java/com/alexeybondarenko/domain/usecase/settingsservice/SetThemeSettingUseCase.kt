package com.alexeybondarenko.domain.usecase.settingsservice

import com.alexeybondarenko.domain.model.SettingKeyEntity
import com.alexeybondarenko.domain.model.ThemeSettingEntity
import com.alexeybondarenko.domain.repository.SettingsService

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