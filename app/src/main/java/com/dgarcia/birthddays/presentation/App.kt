package com.dgarcia.birthddays.presentation

import android.app.Application
import com.dgarcia.birthddays.BuildConfig
import com.dgarcia.birthddays.data.di.databaseModule
import com.dgarcia.birthddays.data.di.networkingModule
import com.dgarcia.birthddays.data.di.repositoryModule
import com.dgarcia.birthddays.domain.di.interactionModule
import com.dgarcia.birthddays.presentation.di.appModule
import com.dgarcia.birthddays.presentation.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App: Application() {
    companion object {
        lateinit var instance: Application
        private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        startKoin {
            androidContext(this@App)
            if (BuildConfig.DEBUG) androidLogger(Level.DEBUG)
            modules(appModules + domainModules + dataModules)
        }
    }
}

val appModules = listOf(presentationModule, appModule)
val domainModules = listOf(interactionModule)
val dataModules = listOf(networkingModule, repositoryModule, databaseModule)