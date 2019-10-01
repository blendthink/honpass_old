package dev.honwaka_lab.honpass.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import dev.honwaka_lab.honpass.R
import dev.honwaka_lab.honpass.data.entities.Admin
import dev.honwaka_lab.honpass.databinding.ActivityMainBinding
import org.koin.android.ext.android.inject
import org.koin.androidx.scope.currentScope
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class MainActivity : AppCompatActivity() {

    private val mainViewModel by currentScope.viewModel<MainViewModel>(this)

    private val loggedInAdmin by inject<Admin>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DataBindingUtil.setContentView<ActivityMainBinding>(
            this, R.layout.activity_main
        ).apply {

            lifecycleOwner = this@MainActivity

            viewModel = mainViewModel
        }
    }
}