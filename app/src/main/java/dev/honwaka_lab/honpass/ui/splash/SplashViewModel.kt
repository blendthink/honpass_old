package dev.honwaka_lab.honpass.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.honwaka_lab.honpass.convenience.Event
import dev.honwaka_lab.honpass.convenience.Result
import dev.honwaka_lab.honpass.data.repositories.AdminRepository
import dev.honwaka_lab.honpass.ui.login.LoginActivity
import dev.honwaka_lab.honpass.ui.register.RegisterActivity
import dev.honwaka_lab.honpass.ui.splash.model.TransitionData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

internal class SplashViewModel(
    private val adminRepository: AdminRepository
) : ViewModel() {

    private val _openActivityEvent: MutableLiveData<Event<TransitionData<*>>> = MutableLiveData()

    val openActivityEvent: LiveData<Event<TransitionData<*>>> = _openActivityEvent

    fun activateOpenActivityEvent() {

        viewModelScope.launch {

            val result = withContext(Dispatchers.IO) {
                adminRepository.isRegistered()
            }

            val destinationActivityClass = when (result) {
                is Result.Success -> LoginActivity::class.java
                is Result.Error -> RegisterActivity::class.java
            }

            _openActivityEvent.value = Event(TransitionData(destinationActivityClass))
        }
    }
}