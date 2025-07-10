package com.alexeybondarenko.domain.service.settings.usecase

import com.alexeybondarenko.domain.models.LanguageSettingEntity
import com.alexeybondarenko.domain.models.SettingKeyEntity
import com.alexeybondarenko.domain.service.settings.repository.SettingsService

class SetLanguageSettingUseCase(
    private val settingsService: SettingsService,
) {
    fun execute(newLanguageSettingValue: LanguageSettingEntity) {
        settingsService.saveSetting(
            key = SettingKeyEntity.LANGUAGE,
            value = newLanguageSettingValue,
        )
    }
}