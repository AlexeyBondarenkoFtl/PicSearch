package com.alexeybondarenko.picsearch

import android.app.Application
import com.alexeybondarenko.picsearch.di.appModule
import com.alexeybondarenko.picsearch.di.imageSearchModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class PicSearchApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@PicSearchApplication)
            modules(
                appModule,
                imageSearchModel,
            )
        }
    }
}