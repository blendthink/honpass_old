package dev.honwaka_lab.honpass.ui.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import dev.honwaka_lab.honpass.R
import dev.honwaka_lab.honpass.databinding.ActivityRegisterBinding
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

internal class RegisterActivity : AppCompatActivity() {

    private val registerViewModel by inject<RegisterViewModel> {
        parametersOf(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DataBindingUtil.setContentView<ActivityRegisterBinding>(
            this, R.layout.activity_register
        ).apply {

            viewModel = registerViewModel.apply {

                password.observe(this@RegisterActivity, Observer {

                    changeSubmitEnabled()
                })

                passwordForConfirm.observe(this@RegisterActivity, Observer {

                    changeSubmitEnabled()
                })

                enabledSubmitButton.observe(this@RegisterActivity, Observer {

                    Toast.makeText(this@RegisterActivity, "${enabledSubmitButton.value}", Toast.LENGTH_LONG).show()
                })
            }
        }
    }
}
