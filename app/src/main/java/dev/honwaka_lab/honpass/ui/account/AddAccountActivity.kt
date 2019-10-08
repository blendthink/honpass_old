package dev.honwaka_lab.honpass.ui.account

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import dev.honwaka_lab.honpass.R
import dev.honwaka_lab.honpass.databinding.ActivityAddAcountBinding
import org.koin.androidx.scope.currentScope
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class AddAccountActivity : AppCompatActivity() {

    private val addAccountViewModel by currentScope.viewModel<AddAccountViewModel>(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DataBindingUtil.setContentView<ActivityAddAcountBinding>(
            this, R.layout.activity_add_acount
        ).apply {

            lifecycleOwner = this@AddAccountActivity

            viewModel = addAccountViewModel
        }
    }
}
