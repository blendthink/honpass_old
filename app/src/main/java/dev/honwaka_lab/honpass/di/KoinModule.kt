package dev.honwaka_lab.honpass.di

import android.app.Activity
import androidx.room.Room
import dev.honwaka_lab.honpass.data.HonpassDatabase
import dev.honwaka_lab.honpass.data.repositories.AdminRepository
import dev.honwaka_lab.honpass.ui.register.RegisterViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.ext.koin.viewModel
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

    fun registerModule() = module {

        factory {
            get<HonpassDatabase>().adminDao()
        }

        viewModel {
            (activity: Activity) -> RegisterViewModel(activity)
        }

        single {
            AdminRepository(get())
        }
    }
}