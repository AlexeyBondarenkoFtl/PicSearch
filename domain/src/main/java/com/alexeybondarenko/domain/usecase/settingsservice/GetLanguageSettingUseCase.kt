package com.alexeybondarenko.domain.usecase.settingsservice

import com.alexeybondarenko.domain.model.LanguageSettingEntity
import com.alexeybondarenko.domain.model.SettingKeyEntity
import com.alexeybondarenko.domain.repository.SettingsService

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