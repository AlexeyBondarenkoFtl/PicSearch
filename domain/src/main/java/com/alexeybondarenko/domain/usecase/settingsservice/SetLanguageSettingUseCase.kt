package com.alexeybondarenko.domain.usecase.settingsservice

import com.alexeybondarenko.domain.model.LanguageSettingEntity
import com.alexeybondarenko.domain.model.SettingKeyEntity
import com.alexeybondarenko.domain.repository.SettingsService

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