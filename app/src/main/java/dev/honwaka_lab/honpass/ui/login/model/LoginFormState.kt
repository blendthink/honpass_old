package dev.honwaka_lab.honpass.ui.login.model

internal data class LoginFormState(
    val passwordError: PasswordError = PasswordError.NONE,
    val isDataValid: Boolean = false
)
