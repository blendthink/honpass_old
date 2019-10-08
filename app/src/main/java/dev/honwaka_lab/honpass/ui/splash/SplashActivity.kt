package dev.honwaka_lab.honpass.ui.splash

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import dev.honwaka_lab.honpass.R
import dev.honwaka_lab.honpass.convenience.Result
import dev.honwaka_lab.honpass.databinding.ActivitySplashBinding
import dev.honwaka_lab.honpass.ui.login.LoginActivity
import dev.honwaka_lab.honpass.ui.register.RegisterActivity
import org.koin.androidx.scope.currentScope
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class SplashActivity : AppCompatActivity() {

    private val splashViewModel by currentScope.viewModel<SplashViewModel>(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DataBindingUtil.setContentView<ActivitySplashBinding>(
            this, R.layout.activity_splash
        ).apply {

            lifecycleOwner = this@SplashActivity

            viewModel = splashViewModel.apply {

                isRegisteredResult.observe(this@SplashActivity, Observer {

                    when (it) {
                        is Result.Success -> succeedToCheckIfRegistered(it.data)
                        is Result.Error -> failToCheckIfRegistered(it.exception)
                    }
                })
            }
        }

        Handler().postDelayed({
            splashViewModel.checkIfRegistered()
        }, 800)
    }

    private fun succeedToCheckIfRegistered(isRegistered: Boolean) {

        val destinationActivityClass = if (isRegistered) {
            LoginActivity::class.java
        } else {
            RegisterActivity::class.java
        }

        openActivity(destinationActivityClass)
    }

    private fun failToCheckIfRegistered(e: Exception) {
        Toast.makeText(applicationContext, e.message, Toast.LENGTH_LONG).show()
    }

    private fun <T : Activity> openActivity(destinationActivityClass: Class<T>) {
        val intent = Intent(this, destinationActivityClass)
        startActivity(intent)
        finish()
    }
}
