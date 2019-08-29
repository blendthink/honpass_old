package dev.honwaka_lab.honpass.ui.register

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.lifecycle.*
import dev.honwaka_lab.honpass.utils.HashUtil
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.security.SecureRandom

internal class RegisterViewModel(
    private val activity: Activity
) : ViewModel() {

    private val inputMethodManager =
        activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

    val password = MutableLiveData<String>()
    val passwordForConfirm = MutableLiveData<String>()

    val enabledSubmitButton = MediatorLiveData<Boolean>()

    init {

        val observerEnabledSubmitButton = Observer<String> {
            enabledSubmitButton.value = canSubmit()
        }

        enabledSubmitButton.addSource(password, observerEnabledSubmitButton)
        enabledSubmitButton.addSource(passwordForConfirm, observerEnabledSubmitButton)
    }

    fun submit(view: View) {

        // TODO: 後々動的にする
        val name = "default"

        Toast.makeText(
            activity,
            "${password.value} : ${passwordForConfirm.value}",
            Toast.LENGTH_LONG
        ).show()

        val passwordValue = password.value ?: ""

        val hash = HashUtil.encode(passwordValue)

        val result = HashUtil.match(passwordValue, hash)

        clearFocus(view)

        hideKeyboard(view)
    }

    fun clickScreen(view: View) {

        clearFocus(view)

        hideKeyboard(view)
    }

    private fun canSubmit(): Boolean {

        val passwordValue = password.value ?: return false

        val length = passwordValue.length

        if (length !in 6..36) {
            return false
        }

        if (password.value != passwordForConfirm.value) {
            return false
        }

        return true
    }

    private fun clearFocus(view: View) {

        view.requestFocus()

        activity.currentFocus?.clearFocus()
    }

    private fun hideKeyboard(view: View) {
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}