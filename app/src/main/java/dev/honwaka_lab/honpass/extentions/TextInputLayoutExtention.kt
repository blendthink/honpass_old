package dev.honwaka_lab.honpass.extentions

import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("errorMessage")
fun TextInputLayout.setErrorMessage(errorMessage: String?) {
    error = errorMessage
}
