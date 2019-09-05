package dev.honwaka_lab.honpass.ui.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import dev.honwaka_lab.honpass.R
import dev.honwaka_lab.honpass.convenience.Result
import dev.honwaka_lab.honpass.databinding.ActivityRegisterBinding
import dev.honwaka_lab.honpass.ui.login.LoginActivity
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import java.lang.Exception

internal class RegisterActivity : AppCompatActivity() {

    private val registerViewModel by inject<RegisterViewModel> {
        parametersOf(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
}
