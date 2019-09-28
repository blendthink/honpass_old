package dev.honwaka_lab.honpass.ui.splash

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import dev.honwaka_lab.honpass.R
import dev.honwaka_lab.honpass.databinding.ActivitySplashBinding
import org.koin.android.ext.android.inject

internal class SplashActivity : AppCompatActivity() {

    private val splashViewModel by inject<SplashViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DataBindingUtil.setContentView<ActivitySplashBinding>(
            this, R.layout.activity_splash
        ).apply {

            lifecycleOwner = this@SplashActivity

            viewModel = splashViewModel.apply {

                openActivityEvent.observe(this@SplashActivity, Observer {

                    val event = openActivityEvent.value ?: return@Observer

                    val transitionData = event.getContentIfNotHandled() ?: return@Observer

                    openActivity(transitionData.destinationActivityClass)
                })
            }
        }

        Handler().postDelayed({
            splashViewModel.activateOpenActivityEvent()
        }, 800)
    }

    private fun <T : Activity> openActivity(activityClass: Class<T>) {
        val intent = Intent(this, activityClass)
        startActivity(intent)
        finish()
    }
}
