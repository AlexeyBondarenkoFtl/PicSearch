package com.alexeybondarenko.picsearch.ui.settings

data class SettingsViewModelState(
    val currentApi: ApiSelection = ApiSelection.UNSPLASH,
    val currentTheme: ThemeSelection = ThemeSelection.LIGHT,
    val currentLanguage: LanguageSelection = LanguageSelection.RU,
)

interface Selection {
    val title: String
}

enum class ApiSelection(override val title: String) : Selection {
    UNSPLASH("Unsplash"),
    PINTEREST("Pinterest");
}

enum class ThemeSelection(override val title: String) : Selection {
    LIGHT("Светлая"),
    DARK("Темная"),
    SYSTEM("Системная");
}

enum class LanguageSelection(override val title: String) : Selection {
    RU("RU"),
    EN("EN");
}
