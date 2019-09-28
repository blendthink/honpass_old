package dev.honwaka_lab.honpass.di

import androidx.room.Room
import dev.honwaka_lab.honpass.data.HonpassDatabase
import dev.honwaka_lab.honpass.data.repositories.AdminRepository
import dev.honwaka_lab.honpass.ui.register.RegisterViewModel
import dev.honwaka_lab.honpass.ui.splash.SplashViewModel
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

        factory {
            get<HonpassDatabase>().adminDao()
        }

        single {
            AdminRepository(get())
        }
    }

    fun splashModule() = module {

        viewModel {
            SplashViewModel(get())
        }
    }

    fun registerModule() = module {

        viewModel {
            RegisterViewModel(get())
        }
    }
}