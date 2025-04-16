package com.farimarwat.loom

import android.app.Application
import com.farimarwat.loom.di.initKoin
import org.koin.android.ext.koin.androidContext

class BaseApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@BaseApplication)
        }
    }
}