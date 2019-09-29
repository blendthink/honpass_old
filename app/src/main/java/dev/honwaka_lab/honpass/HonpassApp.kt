package dev.honwaka_lab.honpass

import android.app.Application
import dev.honwaka_lab.honpass.di.KoinModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

internal class HonpassApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@HonpassApp)
            modules(listOf(
                KoinModule.appModule(),
                KoinModule.splashModule(),
                KoinModule.registerModule(),
                KoinModule.loginModule(),
                KoinModule.mainModule()
            ))
        }
    }
}