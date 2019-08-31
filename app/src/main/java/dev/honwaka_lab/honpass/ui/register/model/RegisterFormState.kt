package dev.honwaka_lab.honpass.ui.register.model

internal data class RegisterFormState(
    val passwordError: PasswordError? = null,
    val passwordForConfirmError: PasswordForConfirmError? = null,
    val isDataValid: Boolean = false
)




internal data class PasswordFormState(
    val passwordError: PasswordError?
)