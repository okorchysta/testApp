package com.example.testapp

import android.app.Application
import com.example.api.di.repositoryModule
import com.example.base_core.baseNetModule
import com.example.testapp.di.mainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@App)
            modules(listOf(baseNetModule(BASE_URL), repositoryModule, mainModule))
        }
    }
}