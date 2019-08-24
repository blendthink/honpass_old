package dev.honwaka_lab.honpass.ui.register

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import dev.honwaka_lab.honpass.R
import dev.honwaka_lab.honpass.data.dao.AdminDao
import dev.honwaka_lab.honpass.data.repositories.AdminRepository
import dev.honwaka_lab.honpass.databinding.ActivityRegisterBinding
import kotlinx.android.synthetic.main.activity_register.*

internal class RegisterActivity : AppCompatActivity() {

    private val inputMethodManager: InputMethodManager by lazy {
        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DataBindingUtil.setContentView<ActivityRegisterBinding>(
            this, R.layout.activity_register
        ).apply {
//            viewModel = RegisterViewModel()
        }
    }

    override fun onPause() {
        super.onPause()
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
