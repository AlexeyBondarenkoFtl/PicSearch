package com.alexeybondarenko.data.repository.utils

import com.alexeybondarenko.domain.model.ApiSettingEntity
import com.alexeybondarenko.domain.model.LanguageSettingEntity
import com.alexeybondarenko.domain.model.SettingEntity
import com.alexeybondarenko.domain.model.ThemeSettingEntity
import com.alexeybondarenko.domain.utils.Mapper

class SettingsEntityMapper : Mapper<String, SettingEntity>() {
    override fun mapToEntity(from: String): SettingEntity? {
        return when (from) {
            UNSPLASH -> ApiSettingEntity.UNSPLASH
            PINTEREST -> ApiSettingEntity.PINTEREST

            LIGHT -> ThemeSettingEntity.LIGHT
            DARK -> ThemeSettingEntity.DARK
            SYSTEM -> ThemeSettingEntity.SYSTEM

            RU -> LanguageSettingEntity.RU
            EN -> LanguageSettingEntity.EN

            else -> null
        }
    }

    override fun mapFromEntity(from: SettingEntity): String {
        return when (from) {
            is ApiSettingEntity ->
                when (from) {
                    ApiSettingEntity.UNSPLASH -> UNSPLASH
                    ApiSettingEntity.PINTEREST -> PINTEREST
                }

            is ThemeSettingEntity ->
                when (from) {
                    ThemeSettingEntity.LIGHT -> LIGHT
                    ThemeSettingEntity.DARK -> DARK
                    ThemeSettingEntity.SYSTEM -> SYSTEM
                }

            is LanguageSettingEntity ->
                when (from) {
                    LanguageSettingEntity.RU -> RU
                    LanguageSettingEntity.EN -> EN
                }

            else -> ""
        }
    }

    companion object {
        private const val UNSPLASH = "unsplash"
        private const val PINTEREST = "pinterest"

        private const val LIGHT = "light"
        private const val DARK = "dark"
        private const val SYSTEM = "system"

        private const val RU = "ru"
        private const val EN = "en"
    }
}