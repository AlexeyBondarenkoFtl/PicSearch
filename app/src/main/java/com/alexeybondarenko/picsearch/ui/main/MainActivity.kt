package com.alexeybondarenko.picsearch.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.alexeybondarenko.picsearch.ui.utils.theme.PicSearchTheme
import org.koin.compose.KoinContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PicSearchTheme {
                KoinContext {
                    MainScreen()
                }
            }
        }
    }
}