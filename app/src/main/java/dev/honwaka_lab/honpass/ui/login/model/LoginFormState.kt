package dev.honwaka_lab.honpass.ui.login.model

/**
 * Data validation state of the login form.
 */
internal data class LoginFormState(
    val usernameError: Int? = null,
    val passwordError: Int? = null,
    val isDataValid: Boolean = false
)
