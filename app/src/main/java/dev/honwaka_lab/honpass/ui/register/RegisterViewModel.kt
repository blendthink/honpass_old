package dev.honwaka_lab.honpass.ui.register

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.*

internal class RegisterViewModel(
    app: Application
) : AndroidViewModel(app) {

    var password = MutableLiveData<String>()
    var passwordForConfirm = MutableLiveData<String>()

    fun submit() {

        // TODO: 後々動的にする
        val name = "default"

        Toast.makeText(
            getApplication(),
            "${password.value} : ${passwordForConfirm.value}",
            Toast.LENGTH_LONG
        ).show()
    }
}