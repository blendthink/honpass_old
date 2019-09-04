package dev.honwaka_lab.honpass.ui.register

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.lifecycle.*
import dev.honwaka_lab.honpass.ui.register.model.RegisterFormState
import dev.honwaka_lab.honpass.utils.HashUtil

internal class RegisterViewModel(
    private val activity: Activity
) : ViewModel() {

    private val inputMethodManager =
        activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

    val password = MutableLiveData<String>()
    val passwordForConfirm = MutableLiveData<String>()

    val formState = MediatorLiveData<RegisterFormState>()

    init {

        formState.value = RegisterFormState()

        val observerFormState = Observer<String> {
            formState.value = RegisterFormState.newInstance(password.value, passwordForConfirm.value)
        }

        formState.addSource(password, observerFormState)
        formState.addSource(passwordForConfirm, observerFormState)
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

    private fun clearFocus(view: View) {

        view.requestFocus()

        activity.currentFocus?.clearFocus()
    }

    private fun hideKeyboard(view: View) {
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}