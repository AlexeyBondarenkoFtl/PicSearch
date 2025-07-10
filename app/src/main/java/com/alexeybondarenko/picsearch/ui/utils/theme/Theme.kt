package com.alexeybondarenko.picsearch.ui.utils.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import com.alexeybondarenko.domain.models.ThemeSettingEntity

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40
)

@Composable
fun PicSearchTheme(
    content: @Composable () -> Unit,
    themeSetting: ThemeSettingEntity,
) {
    val colorScheme = when (themeSetting) {
        ThemeSettingEntity.LIGHT -> LightColorScheme
        ThemeSettingEntity.DARK -> DarkColorScheme
        ThemeSettingEntity.SYSTEM -> {
            if (isSystemInDarkTheme()) {
                DarkColorScheme
            } else {
                LightColorScheme
            }
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}