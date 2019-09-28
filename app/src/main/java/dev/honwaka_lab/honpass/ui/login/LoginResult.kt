package dev.honwaka_lab.honpass.ui.login

/**
 * Authentication isRegisteredResult : success (user details) or error message.
 */
data class LoginResult(
    val success: LoggedInUserView? = null,
    val error: Int? = null
)
