package dev.honwaka_lab.honpass.ui.splash.model

import android.app.Activity

internal data class TransitionData<T: Activity>(val destinationActivityClass: Class<T>)