package dev.honwaka_lab.honpass.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import dev.honwaka_lab.honpass.R
import dev.honwaka_lab.honpass.ui.register.RegisterActivity

internal class SplashActivity : AppCompatActivity() {

    private var handler: Handler? = null

    private val runnable = Runnable {

        // TODO: 遷移先を分離？
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        handler = Handler()
    }

    override fun onResume() {
        super.onResume()

        handler?.postDelayed(runnable, 800)
    }

    override fun onPause() {
        super.onPause()

        handler?.removeCallbacks(runnable)
    }
}
