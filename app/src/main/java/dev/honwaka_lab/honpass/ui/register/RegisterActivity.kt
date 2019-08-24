package dev.honwaka_lab.honpass.ui.register

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import dev.honwaka_lab.honpass.R
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
            viewModel = RegisterViewModel(application).apply {

                password.observe(this@RegisterActivity,
                    Observer<String> {
                        Toast.makeText(this@RegisterActivity, it, Toast.LENGTH_LONG).show()
                    }
                )

                passwordForConfirm.observe(this@RegisterActivity,

                    Observer<String> {

                        if (password.value == passwordForConfirm.value) {
                            Toast.makeText(this@RegisterActivity, "同じ", Toast.LENGTH_LONG).show()
                        } else {
                            Toast.makeText(this@RegisterActivity, "違う", Toast.LENGTH_LONG).show()
                        }
                    }
                )
            }
        }
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
