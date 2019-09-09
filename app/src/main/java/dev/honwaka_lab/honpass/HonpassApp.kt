package dev.honwaka_lab.honpass

import android.app.Application
import dev.honwaka_lab.honpass.di.KoinModule
import org.koin.android.ext.android.startKoin

internal class HonpassApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin(
            androidContext = applicationContext,
            modules = listOf(
                KoinModule.appModule(),
                KoinModule.splashModule(),
                KoinModule.registerModule()
            )
        )
    }
}