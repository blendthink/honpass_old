package dev.honwaka_lab.honpass.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import dev.honwaka_lab.honpass.R
import dev.honwaka_lab.honpass.data.entities.Admin
import dev.honwaka_lab.honpass.databinding.ActivityMainBinding
import org.koin.android.ext.android.inject

internal class MainActivity : AppCompatActivity() {

    private val mainViewModel by inject<MainViewModel>()

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