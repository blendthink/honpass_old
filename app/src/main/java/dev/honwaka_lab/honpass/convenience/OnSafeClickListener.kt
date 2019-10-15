package dev.honwaka_lab.honpass.convenience

import android.os.SystemClock
import android.view.View

internal abstract class OnSafeClickListener : View.OnClickListener {

    private var lastClickTime: Long = 0

    override fun onClick(v: View?) {

        val currentTime = SystemClock.elapsedRealtime()
        if (currentTime - lastClickTime < MIN_CLICK_INTERVAL) {
            return
        }

        lastClickTime = currentTime
        onSafeClick(v)
    }

    abstract fun onSafeClick(v: View?)

    companion object {
        const val MIN_CLICK_INTERVAL = 1000
    }
}