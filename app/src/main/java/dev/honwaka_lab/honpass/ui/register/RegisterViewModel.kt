package dev.honwaka_lab.honpass.ui.register

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.*
import dev.honwaka_lab.honpass.data.repositories.AdminRepository
import dev.honwaka_lab.honpass.ui.register.model.RegisterFormState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

internal class RegisterViewModel(
    private val activity: Activity,
    private val adminRepository: AdminRepository
) : ViewModel() {

    private val inputMethodManager =
        activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

    val password = MutableLiveData<String>()
    val passwordForConfirm = MutableLiveData<String>()

    val formState = MediatorLiveData<RegisterFormState>()

    init {

        formState.value = RegisterFormState()

        val observerFormState = Observer<String> {
            formState.value = RegisterFormState.newInstance(password.value, passwordForConfirm.value)
        }

        formState.addSource(password, observerFormState)
        formState.addSource(passwordForConfirm, observerFormState)
    }

    fun submit(view: View) {

        clearFocus(view)

        hideKeyboard(view)

        val passwordValue = password.value
            ?: throw RuntimeException("入力ミスを防げていません")

        viewModelScope.launch {

            withContext(Dispatchers.IO) {
                adminRepository.register(passwordValue)
            }

            withContext(Dispatchers.IO) {
                adminRepository.login(rawPassword = passwordValue)
            }
        }
    }

    fun clickScreen(view: View) {

        clearFocus(view)

        hideKeyboard(view)
    }

    private fun clearFocus(view: View) {

        view.requestFocus()

        activity.currentFocus?.clearFocus()
    }

    private fun hideKeyboard(view: View) {
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}