package dev.honwaka_lab.honpass.di

import androidx.room.Room
import dev.honwaka_lab.honpass.data.HonpassDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module

internal object KoinModule {

    fun appModule() = module {

        single {
            Room.databaseBuilder(
                androidContext(),
                HonpassDatabase::class.java,
                "honpass.db"
            ).build()
        }
    }
}