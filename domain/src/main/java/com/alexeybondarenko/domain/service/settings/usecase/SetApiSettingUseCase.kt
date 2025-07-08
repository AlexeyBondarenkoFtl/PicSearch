package com.alexeybondarenko.domain.service.settings.usecase

import com.alexeybondarenko.domain.models.ApiSettingEntity
import com.alexeybondarenko.domain.models.SettingKeyEntity
import com.alexeybondarenko.domain.service.settings.repository.SettingsService

class SetApiSettingUseCase(
    private val settingsService: SettingsService,
) {
    fun execute(newApiSettingValue: ApiSettingEntity) {
        settingsService.saveSetting(
            key = SettingKeyEntity.API,
            value = newApiSettingValue,
        )
    }
}