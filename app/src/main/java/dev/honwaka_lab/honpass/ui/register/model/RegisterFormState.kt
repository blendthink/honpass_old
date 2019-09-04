package dev.honwaka_lab.honpass.ui.register.model

internal class RegisterFormState(
    var passwordError: PasswordError = PasswordError.NONE,
    var passwordForConfirmError: PasswordForConfirmError = PasswordForConfirmError.NONE,
    var isDataValid: Boolean = false
) {

    fun update(password: String?, passwordForConfirm: String?): RegisterFormState {

        passwordError = updatePasswordError(password)

        passwordForConfirmError = updatePasswordForConfirmError(password, passwordForConfirm)

        isDataValid = updateIsDataValid(password, passwordForConfirm)

        return RegisterFormState(passwordError, passwordForConfirmError, isDataValid)
    }

    private fun updatePasswordError(password: String?): PasswordError {

        if (password.isNullOrEmpty()) {
            return PasswordError.NONE
        }

        val length = password.length
        if (length < 10) {
            return PasswordError.LENGTH
        }

        return if (password.matches(PASSWORD_REGEX.toRegex())) {
            PasswordError.NONE
        } else {
            PasswordError.TYPE
        }
    }

    private fun updatePasswordForConfirmError(
        password: String?,
        passwordForConfirm: String?
    ): PasswordForConfirmError {

        return if (password == passwordForConfirm) {
            PasswordForConfirmError.NONE
        } else {
            PasswordForConfirmError.DIFFERENT
        }
    }

    private fun updateIsDataValid(password: String?, passwordForConfirm: String?): Boolean {

        if (passwordError != PasswordError.NONE) return false

        if (passwordForConfirmError != PasswordForConfirmError.NONE) return false

        return !password.isNullOrEmpty() && !passwordForConfirm.isNullOrEmpty()
    }

    companion object {
        private const val PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!-/:-@\\[-`{-~])[!-~]{10,36}$"
    }
}