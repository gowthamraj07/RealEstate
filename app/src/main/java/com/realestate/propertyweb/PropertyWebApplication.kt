package com.realestate.propertyweb

import android.app.Application
import appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class PropertyWebApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@PropertyWebApplication)
            modules(appModule)
        }
    }
}