package dev.honwaka_lab.honpass.ui.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
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

            lifecycleOwner = this@RegisterActivity

            viewModel = registerViewModel
        }
    }
}
