package dev.honwaka_lab.honpass.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import dev.honwaka_lab.honpass.R
import dev.honwaka_lab.honpass.convenience.Result
import dev.honwaka_lab.honpass.data.entities.Admin
import dev.honwaka_lab.honpass.databinding.ActivityLoginBinding
import dev.honwaka_lab.honpass.di.KoinModule
import dev.honwaka_lab.honpass.ui.main.MainActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

internal class LoginActivity : AppCompatActivity() {

    private val loginViewModel by viewModel<LoginViewModel>()

    private lateinit var inputMethodManager: InputMethodManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        DataBindingUtil.setContentView<ActivityLoginBinding>(
            this, R.layout.activity_login
        ).apply {

            lifecycleOwner = this@LoginActivity

            viewModel = loginViewModel.apply {

                result.observe(this@LoginActivity, Observer {

                    when (it) {
                        is Result.Success -> succeedToLogin(it.data)
                        is Result.Error -> failToLogin(it.exception)
                    }
                })

                clearFocusEvent.observe(this@LoginActivity, Observer {
                    clearFocus()
                })

                hideKeyboardEvent.observe(this@LoginActivity, Observer {
                    hideKeyboard()
                })
            }
        }
    }

    private fun succeedToLogin(loggedInAdmin: Admin) {

        // TODO 場所をMainActivityへ
        loadKoinModules(
            KoinModule.mainModule(loggedInAdmin)
        )

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun failToLogin(e: Exception) {
        Toast.makeText(applicationContext, e.message, Toast.LENGTH_LONG).show()
    }

    private fun clearFocus() {
        currentFocus?.clearFocus()
    }

    private fun hideKeyboard() {
        inputMethodManager.hideSoftInputFromWindow(window.decorView.windowToken, 0)
    }
}
