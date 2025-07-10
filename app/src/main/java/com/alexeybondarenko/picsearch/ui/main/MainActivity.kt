package com.alexeybondarenko.picsearch.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.alexeybondarenko.domain.service.settings.repository.SettingsService
import com.alexeybondarenko.picsearch.ui.utils.theme.PicSearchTheme
import org.koin.compose.KoinContext
import org.koin.compose.koinInject

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KoinContext {
                val settingsService = koinInject<SettingsService>()

                val theme by settingsService
                    .observeThemeSetting()
                    .collectAsState(initial = SettingsService.themeDefault)

                PicSearchTheme(
                    themeSetting = theme,
                    content = {
                        MainScreen()
                    }
                )
            }

        }
    }
}