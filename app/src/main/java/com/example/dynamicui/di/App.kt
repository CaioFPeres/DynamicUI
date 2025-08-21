package com.example.dynamicui.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

class DynamicUI : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@DynamicUI)
            modules(appModule)
        }
    }

    override fun onTerminate(){
        super.onTerminate()
        stopKoin()
    }
}
