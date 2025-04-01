package com.alexeybondarenko.domain.model

interface SettingEntity

enum class ApiSettingEntity : SettingEntity {
    UNSPLASH,
    PINTEREST;
}

enum class ThemeSettingEntity : SettingEntity {
    LIGHT,
    DARK,
    SYSTEM,
}

enum class LanguageSettingEntity : SettingEntity {
    RU,
    EN;
}

enum class SettingKeyEntity(
    val value: String,
) {
    API("SETTINGS_API_KEY"),
    THEME("SETTINGS_THEME_KEY"),
    LANGUAGE("SETTINGS_LANGUAGE_KEY");
}