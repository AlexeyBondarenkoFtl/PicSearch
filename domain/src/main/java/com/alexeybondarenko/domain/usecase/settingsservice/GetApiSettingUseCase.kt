package com.alexeybondarenko.domain.usecase.settingsservice

import com.alexeybondarenko.domain.model.ApiSettingEntity
import com.alexeybondarenko.domain.model.SettingKeyEntity
import com.alexeybondarenko.domain.repository.SettingsService

class GetApiSettingUseCase(
    private val settingsService: SettingsService,
) {
    private val defValue = ApiSettingEntity.UNSPLASH

    fun execute(): ApiSettingEntity {
        val setting = settingsService.getSetting(
            key = SettingKeyEntity.API,
            defValue = defValue,
        )

        return setting as? ApiSettingEntity ?: defValue
    }
}