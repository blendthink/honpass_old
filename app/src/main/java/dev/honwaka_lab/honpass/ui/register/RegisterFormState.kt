package dev.honwaka_lab.honpass.ui.register

internal data class RegisterFormState(
    val passwordError: Int? = null,
    val passwordForConfirmError: Int? = null,
    val isDataValid: Boolean = false
)