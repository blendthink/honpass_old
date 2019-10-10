package dev.honwaka_lab.honpass.utils

import android.content.Context

internal class DisplayUtil private constructor() {

    companion object {

        fun dp2px(dp: Float, context: Context) =
            dp * context.resources.displayMetrics.density
    }
}