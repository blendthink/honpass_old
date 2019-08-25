package dev.honwaka_lab.honpass.ui.register

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.lifecycle.*

internal class RegisterViewModel(
    private val activity: Activity
) : ViewModel() {

    private val inputMethodManager =
        activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

    var password = MutableLiveData<String>()
    var passwordForConfirm = MutableLiveData<String>()

    fun submit(view: View) {

        // TODO: 後々動的にする
        val name = "default"

        Toast.makeText(
            activity,
            "${password.value} : ${passwordForConfirm.value}",
            Toast.LENGTH_LONG
        ).show()

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