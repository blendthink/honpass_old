package dev.honwaka_lab.honpass.ui.splash

import androidx.lifecycle.*
import dev.honwaka_lab.honpass.convenience.Result
import dev.honwaka_lab.honpass.data.repositories.AdminRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

internal class SplashViewModel(
    private val adminRepository: AdminRepository
) : ViewModel() {

    private val _isRegisteredResult = MutableLiveData<Result<Boolean>>()
    val isRegisteredResult: LiveData<Result<Boolean>> = _isRegisteredResult

    fun checkIfRegistered() {

        viewModelScope.launch {

            _isRegisteredResult.value = withContext(Dispatchers.IO) {
                adminRepository.isRegistered()
            }
        }
    }
}