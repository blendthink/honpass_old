package dev.honwaka_lab.honpass.ui.main

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.ViewAnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd
import androidx.databinding.DataBindingUtil
import dev.honwaka_lab.honpass.R
import dev.honwaka_lab.honpass.data.entities.Admin
import dev.honwaka_lab.honpass.databinding.ActivityMainBinding
import dev.honwaka_lab.honpass.ui.account.AddAccountActivity
import kotlinx.android.synthetic.main.main_app_bar.*
import org.koin.android.ext.android.inject
import org.koin.androidx.scope.currentScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.math.max

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

        Handler().postDelayed({

            binding.mainAppBar.mainFab.elevation = 0f
            binding.mainAppBar.mainFab.alpha = 0f

            binding.mainReveal.visibility = View.VISIBLE
            val cx = binding.mainReveal.width
            val cy = binding.mainReveal.height

            val fabSize = getFabSize()

            val x = (fabSize / 2 + binding.mainAppBar.mainFab.x).toInt()
            val y = (fabSize / 2 + binding.mainAppBar.mainFab.y).toInt()

            val finalRadius = max(cx, cy) * 1.2f

            ViewAnimationUtils.createCircularReveal(
                binding.mainReveal, x, y, fabSize, finalRadius
            ).apply {

                duration = 500

                doOnEnd {

                    binding.mainReveal.visibility = View.INVISIBLE
                    binding.mainAppBar.mainFab.elevation = 4f

                    val params = binding.mainAppBar.mainFab.layoutParams
                    params.width = fabSize.toInt()
                    binding.mainAppBar.mainFab.requestLayout()

                    Handler().postDelayed({
                        binding.mainAppBar.mainFab.alpha = 1f
                    }, 600)
                }
            }.start()

            openAddAccountActivity()

        }, 300)
    }

    private fun openAddAccountActivity() {

        val intent = Intent(this, AddAccountActivity::class.java)

        Handler().postDelayed({

            startActivity(intent)
            overridePendingTransition(R.anim.none, R.anim.none)

        }, 300)
    }

    private fun getFabSize() : Float {
        return resources.getDimension(R.dimen.fab_size)
    }
}