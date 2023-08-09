package com.developer.samandar.imagefiltersapp.utilities

import android.app.Application
import com.developer.samandar.imagefiltersapp.dependencyinjection.repositoryModule
import com.developer.samandar.imagefiltersapp.dependencyinjection.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

@Suppress("unused")
class AppConfig : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AppConfig)
            modules(listOf(repositoryModule, viewModelModule))
        }
    }
}