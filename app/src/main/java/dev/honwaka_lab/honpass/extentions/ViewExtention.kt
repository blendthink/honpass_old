package dev.honwaka_lab.honpass.extentions

import android.view.View
import androidx.databinding.BindingAdapter
import dev.honwaka_lab.honpass.convenience.OnSafeClickListener

@BindingAdapter("onSafeClick")
fun View.setOnSafeClick(listener: View.OnClickListener) {

    val safeClickListener = object : OnSafeClickListener() {

        override fun onSafeClick(v: View?) {
            listener.onClick(v)
        }
    }

    setOnClickListener(safeClickListener)
}