package dev.honwaka_lab.honpass.extentions

import android.view.ActionMode
import android.view.Menu
import android.view.MenuItem
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputEditText

@BindingAdapter("enabledActionMode")
fun TextInputEditText.setEnabledActionMode(enabled: Boolean) {

    if (enabled) return

    val disableActionModeCallback = object : ActionMode.Callback {

        override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
            return false
        }

        override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
            return false
        }

        override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
            return false
        }

        override fun onDestroyActionMode(mode: ActionMode?) {}
    }

    customSelectionActionModeCallback = disableActionModeCallback
    customInsertionActionModeCallback = disableActionModeCallback
}