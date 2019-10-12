package dev.honwaka_lab.honpass.ui.account

import android.os.Bundle
import android.transition.*
import android.view.View
import android.view.ViewAnimationUtils
import android.view.Window
import android.view.WindowManager
import android.view.animation.AccelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd
import androidx.databinding.DataBindingUtil
import dev.honwaka_lab.honpass.R
import dev.honwaka_lab.honpass.databinding.ActivityAddAcountBinding
import dev.honwaka_lab.honpass.utils.DisplayUtil
import org.koin.androidx.scope.currentScope
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class AddAccountActivity : AppCompatActivity() {

    private val addAccountViewModel by currentScope.viewModel<AddAccountViewModel>(this)

    private lateinit var binding: ActivityAddAcountBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupActivityAnimations()

        binding = DataBindingUtil.setContentView<ActivityAddAcountBinding>(
            this, R.layout.activity_add_acount
        ).apply {

            lifecycleOwner = this@AddAccountActivity

            viewModel = addAccountViewModel
        }

        binding.addAccountFab.postDelayed({

            binding.addAccountFab.animate().apply {
                duration = 300
                alpha(0F)
            }.start()

        }, 600)

        binding.addAccountFab.postDelayed({

            revealView(binding.addAccountLayout) {
                binding.addAccountLayoutCover
                    .animate()
                    .setDuration(300)
                    .alpha(0F)
                    .start()
            }

        }, 600)
    }

    override fun onBackPressed() {

        binding.addAccountLayoutCover
            .animate()
            .setDuration(300)
            .alpha(1F)
            .start()

        hideView(binding.addAccountLayout) {
            super.onBackPressed()
        }
    }

    private fun setupActivityAnimations() {

        val transition = TransitionInflater
            .from(this)
            .inflateTransition(R.transition.fab_change_bounds)

        val transitionSet = TransitionSet().apply {
            addTransition(ChangeImageTransform())
            addTransition(transition)
            ordering = TransitionSet.ORDERING_TOGETHER
            duration = 600
            interpolator = AccelerateInterpolator()
        }

        val slide: Transition = Slide().apply {
            excludeTarget(android.R.id.statusBarBackground, true)
            excludeTarget(android.R.id.navigationBarBackground, true)
        }

        window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)
        window.enterTransition = slide

        window.requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
        window.allowEnterTransitionOverlap = true
        window.sharedElementEnterTransition = transitionSet
        window.sharedElementReturnTransition = transitionSet
        window.sharedElementReenterTransition = transitionSet
        window.sharedElementExitTransition = transitionSet
        window.reenterTransition = slide
        window.exitTransition = slide

        val w = window
        w.setFlags(
            WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS,
            WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS
        )
    }

    private fun revealView(view: View, endAction: () -> Unit) {

        val centerY = view.measuredHeight / 2
        val centerX = view.measuredWidth / 2

        val animator = ViewAnimationUtils.createCircularReveal(
            view, centerX, centerY, 0F, view.width.toFloat()
        ).apply {
            duration = 400
            startDelay = 0
            doOnEnd {
                endAction()
            }
        }

        view.visibility = View.VISIBLE

        animator.start()
    }

    private fun hideView(view: View, endAction: () -> Unit) {

        val centerY = view.measuredHeight / 2
        val centerX = view.measuredWidth / 2

        val animator = ViewAnimationUtils.createCircularReveal(
            view, centerX, centerY, view.height.toFloat(), DisplayUtil.dp2px(32F, this)
        ).apply {
            duration = 800
            doOnEnd {
                binding.addAccountFab.alpha = 1F
                view.visibility = View.INVISIBLE
                endAction()
            }
        }

        animator.start()
    }
}
