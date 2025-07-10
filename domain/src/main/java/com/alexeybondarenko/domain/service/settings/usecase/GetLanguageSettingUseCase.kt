package com.alexeybondarenko.domain.service.settings.usecase

import com.alexeybondarenko.domain.models.LanguageSettingEntity
import com.alexeybondarenko.domain.models.SettingKeyEntity
import com.alexeybondarenko.domain.service.settings.repository.SettingsService

class GetLanguageSettingUseCase(
    private val settingsService: SettingsService,
) {
    private val defValue = SettingsService.languageDefault

    fun execute(): LanguageSettingEntity {
        val setting = settingsService.getSetting(
            key = SettingKeyEntity.LANGUAGE,
            defValue = defValue,
        )

        return setting as? LanguageSettingEntity ?: defValue
    }
}