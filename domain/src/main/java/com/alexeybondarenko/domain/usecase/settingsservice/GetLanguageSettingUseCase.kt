package com.alexeybondarenko.domain.usecase.settingsservice

import com.alexeybondarenko.domain.model.ApiSettingEntity
import com.alexeybondarenko.domain.model.LanguageSettingEntity
import com.alexeybondarenko.domain.model.SettingKeyEntity
import com.alexeybondarenko.domain.model.ThemeSettingEntity
import com.alexeybondarenko.domain.repository.SettingsService

class GetLanguageSettingUseCase(
    private val settingsService: SettingsService,
) {
    fun execute() {
        settingsService.getSetting(
            key = SettingKeyEntity.LANGUAGE,
            defValue = LanguageSettingEntity.RU,
        )
    }
}