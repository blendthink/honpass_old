package dev.honwaka_lab.honpass.ui.login

import android.view.View
import androidx.lifecycle.*
import dev.honwaka_lab.honpass.convenience.Event
import dev.honwaka_lab.honpass.convenience.Result
import dev.honwaka_lab.honpass.data.entities.Admin
import dev.honwaka_lab.honpass.data.repositories.AdminRepository
import dev.honwaka_lab.honpass.ui.login.model.LoginFormState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

internal class LoginViewModel(
    private val adminRepository: AdminRepository
) : ViewModel() {

    val password = MutableLiveData<String>()

    val formState = MediatorLiveData<LoginFormState>()

    private val _result = MutableLiveData<Result<Admin>>()
    val result: LiveData<Result<Admin>> = _result

    private val _hideKeyboardEvent = MutableLiveData<Event<Unit>>()
    val hideKeyboardEvent: LiveData<Event<Unit>> = _hideKeyboardEvent

    private val _clearFocusEvent = MutableLiveData<Event<Unit>>()
    val clearFocusEvent: LiveData<Event<Unit>> = _clearFocusEvent

    init {

        formState.value = LoginFormState()

        // TODO 後から処理を修正
        val observerFormState = Observer<String> {
            formState.value = LoginFormState()
        }

        formState.addSource(password, observerFormState)
    }

    fun submit(view: View) {

        clearFocus(view)

        hideKeyboard()

        val passwordValue = password.value
            ?: throw RuntimeException("入力ミスを防げていません")

        viewModelScope.launch {

            _result.value = withContext(Dispatchers.IO) {
                adminRepository.login(rawPassword = passwordValue)
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
