package com.alexeybondarenko.picsearch

import android.app.Application
import com.alexeybondarenko.picsearch.di.appModule
import com.alexeybondarenko.picsearch.di.imageSearchModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class PicSearchApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@PicSearchApplication)
            modules(
                appModule,
                imageSearchModule,
            )
        }
    }
}