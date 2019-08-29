package dev.honwaka_lab.honpass.extentions

import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("errorText")
fun TextInputLayout.setErrorText(errorText: String?) {
    error = errorText
}