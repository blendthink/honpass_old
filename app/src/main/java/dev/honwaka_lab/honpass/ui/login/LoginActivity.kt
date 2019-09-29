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
import dev.honwaka_lab.honpass.data.entities.Admin
import dev.honwaka_lab.honpass.databinding.ActivityLoginBinding
import org.koin.android.ext.android.inject
import java.lang.Exception

internal class LoginActivity : AppCompatActivity() {

    private val loginViewModel by inject<LoginViewModel>()

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

                    // TODO 結果で処理を分ける
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

    private fun succeedToLogin(admin: Admin) {

        // TODO admin をメモリ上に保存する

        // TODO 遷移先を変更する
        val intent = Intent(this, LoginActivity::class.java)
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
