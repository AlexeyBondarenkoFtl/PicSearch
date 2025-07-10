package com.alexeybondarenko.domain.service.settings.usecase

import com.alexeybondarenko.domain.models.ApiSettingEntity
import com.alexeybondarenko.domain.models.SettingKeyEntity
import com.alexeybondarenko.domain.service.settings.repository.SettingsService

class GetApiSettingUseCase(
    private val settingsService: SettingsService,
) {
    private val defValue = SettingsService.apiDefault

    fun execute(): ApiSettingEntity {
        val setting = settingsService.getSetting(
            key = SettingKeyEntity.API,
            defValue = defValue,
        )

        return setting as? ApiSettingEntity ?: defValue
    }
}