package dev.honwaka_lab.honpass.ui.account

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.honwaka_lab.honpass.convenience.Event

internal class AddAccountViewModel : ViewModel() {

    private val _otherClickEvent = MutableLiveData<Event<Unit>>()
    val otherClickEvent: LiveData<Event<Unit>> = _otherClickEvent

    fun back(v: View) {
        _otherClickEvent.value = Event(Unit)
    }
}
