package dev.honwaka_lab.honpass.ui.register

import android.app.Activity
import android.view.View
import androidx.lifecycle.*
import dev.honwaka_lab.honpass.convenience.Event
import dev.honwaka_lab.honpass.convenience.Result
import dev.honwaka_lab.honpass.data.repositories.AdminRepository
import dev.honwaka_lab.honpass.ui.register.model.RegisterFormState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

internal class RegisterViewModel(
    private val activity: Activity,
    private val adminRepository: AdminRepository
) : ViewModel() {

    val password = MutableLiveData<String>()
    val passwordForConfirm = MutableLiveData<String>()

    val formState = MediatorLiveData<RegisterFormState>()

    private val _result = MutableLiveData<Result<Unit>>()
    val result: LiveData<Result<Unit>> = _result

    private val _hideKeyboardEvent = MutableLiveData<Event<Unit>>()
    val hideKeyboardEvent: LiveData<Event<Unit>> = _hideKeyboardEvent

    private val _clearFocusEvent = MutableLiveData<Event<Unit>>()
    val clearFocusEvent: LiveData<Event<Unit>> = _clearFocusEvent

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

        hideKeyboard()

        val passwordValue = password.value
            ?: throw RuntimeException("入力ミスを防げていません")

        viewModelScope.launch {

            _result.value = withContext(Dispatchers.IO) {
                adminRepository.register(passwordValue)
            }
        }
    }

    fun clickScreen(view: View) {

        clearFocus(view)

        hideKeyboard()
    }

    private fun clearFocus(view: View) {

        view.requestFocus()

        _clearFocusEvent.value = Event(Unit)
    }

    private fun hideKeyboard() {
        _hideKeyboardEvent.value = Event(Unit)
    }
}