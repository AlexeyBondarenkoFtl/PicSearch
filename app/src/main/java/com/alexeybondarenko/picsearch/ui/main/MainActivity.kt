package com.alexeybondarenko.picsearch.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.alexeybondarenko.picsearch.ui.saved.SavedScreen
import com.alexeybondarenko.picsearch.ui.search.SearchScreen
import com.alexeybondarenko.picsearch.ui.settings.SettingsScreen
import com.alexeybondarenko.picsearch.ui.utils.theme.PicSearchTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PicSearchTheme {
                MainScreen(
                    searchScreen = { SearchScreen() },
                    savedScreen = { SavedScreen() },
                    settingsScreen = { SettingsScreen() },
                )
            }
        }
    }
}