package dev.honwaka_lab.honpass.ui.main

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.ViewAnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd
import androidx.core.app.ActivityOptionsCompat
import androidx.databinding.DataBindingUtil
import dev.honwaka_lab.honpass.R
import dev.honwaka_lab.honpass.data.entities.Admin
import dev.honwaka_lab.honpass.databinding.ActivityMainBinding
import dev.honwaka_lab.honpass.ui.account.AddAccountActivity
import kotlin.math.max
import kotlinx.android.synthetic.main.main_app_bar.*
import org.koin.android.ext.android.inject
import org.koin.androidx.scope.currentScope
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class MainActivity : AppCompatActivity() {

    private val mainViewModel by currentScope.viewModel<MainViewModel>(this)

    private val loggedInAdmin by inject<Admin>()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView<ActivityMainBinding>(
            this, R.layout.activity_main
        ).apply {

            lifecycleOwner = this@MainActivity

            viewModel = mainViewModel
        }

        main_fab.setOnClickListener {
            clickFab()
        }
    }

    private fun clickFab() {
        openAddAccountActivity()
    }

    private fun openAddAccountActivity() {

        val intent = Intent(this, AddAccountActivity::class.java)

        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
            this,
            main_fab,
            "fab_transition"
        )

        startActivity(intent, options.toBundle())
    }
}
