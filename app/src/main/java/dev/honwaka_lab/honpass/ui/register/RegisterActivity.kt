package dev.honwaka_lab.honpass.ui.register

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import dev.honwaka_lab.honpass.R
import kotlinx.android.synthetic.main.activity_register.*

internal class RegisterActivity : AppCompatActivity() {

    private val inputMethodManager: InputMethodManager by lazy {
        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
    }

    fun clickScreen(view: View) {

        clearFocus(view)

        hideKeyboard()
    }

    fun submit(view: View) {

        clearFocus(view)

        hideKeyboard()
    }

    private fun clearFocus(view: View) {

        view.requestFocus()

        register_password.clearFocus()
        register_confirm_password.clearFocus()
    }

    private fun hideKeyboard() {
        inputMethodManager.hideSoftInputFromWindow(window.decorView.windowToken, 0)
    }
}
