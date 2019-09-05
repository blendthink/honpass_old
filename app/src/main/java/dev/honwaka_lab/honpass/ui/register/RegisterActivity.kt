package dev.honwaka_lab.honpass.ui.register

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import dev.honwaka_lab.honpass.R
import dev.honwaka_lab.honpass.convenience.Result
import dev.honwaka_lab.honpass.databinding.ActivityRegisterBinding
import dev.honwaka_lab.honpass.ui.login.LoginActivity
import org.koin.android.ext.android.inject
import java.lang.Exception

internal class RegisterActivity : AppCompatActivity() {

    private val registerViewModel by inject<RegisterViewModel>()

    private lateinit var inputMethodManager: InputMethodManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        DataBindingUtil.setContentView<ActivityRegisterBinding>(
            this, R.layout.activity_register
        ).apply {

            lifecycleOwner = this@RegisterActivity

            viewModel = registerViewModel.apply {

                result.observe(this@RegisterActivity, Observer {

                    when (it) {

                        is Result.Success -> {
                            succeedToRegister()
                        }

                        is Result.Error -> {
                            failToRegister(it.exception)
                        }
                    }
                })

                clearFocusEvent.observe(this@RegisterActivity, Observer {
                    clearFocus()
                })

                hideKeyboardEvent.observe(this@RegisterActivity, Observer {
                    hideKeyboard()
                })
            }
        }
    }

    private fun succeedToRegister() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun failToRegister(e: Exception) {
        Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
    }

    private fun clearFocus() {
        currentFocus?.clearFocus()
    }

    private fun hideKeyboard() {
        inputMethodManager.hideSoftInputFromWindow(window.decorView.windowToken, 0)
    }
}
