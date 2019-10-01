package dev.honwaka_lab.honpass.di

import androidx.room.Room
import dev.honwaka_lab.honpass.data.HonpassDatabase
import dev.honwaka_lab.honpass.data.entities.Admin
import dev.honwaka_lab.honpass.data.repositories.AdminRepository
import dev.honwaka_lab.honpass.ui.login.LoginActivity
import dev.honwaka_lab.honpass.ui.login.LoginViewModel
import dev.honwaka_lab.honpass.ui.main.MainActivity
import dev.honwaka_lab.honpass.ui.main.MainViewModel
import dev.honwaka_lab.honpass.ui.register.RegisterActivity
import dev.honwaka_lab.honpass.ui.register.RegisterViewModel
import dev.honwaka_lab.honpass.ui.splash.SplashActivity
import dev.honwaka_lab.honpass.ui.splash.SplashViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

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

    fun loggedInModule(loggedInAdmin: Admin) = module {

        scope(named<MainActivity>()) {
            scoped {
                loggedInAdmin
            }
        }
    }

    fun splashModule() = module {

        scope(named<SplashActivity>()) {
            viewModel {
                SplashViewModel(get())
            }
        }
    }

    fun registerModule() = module {

        scope(named<RegisterActivity>()) {
            viewModel {
                RegisterViewModel(get())
            }
        }
    }

    fun loginModule() = module {

        scope(named<LoginActivity>()) {
            viewModel {
                LoginViewModel(get())
            }
        }
    }

    fun mainModule() = module {

        scope(named<MainActivity>()) {
            viewModel {
                MainViewModel()
            }
        }
    }
}