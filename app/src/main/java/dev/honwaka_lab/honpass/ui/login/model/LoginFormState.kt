package dev.honwaka_lab.honpass.ui.login.model

internal data class LoginFormState(
    val passwordError: PasswordError = PasswordError.NONE,
    val isDataValid: Boolean = false
) {

    companion object {

        // TODO 後々、init()で判別してインスタンス化するように修正する
        fun newInstance(password: String?): LoginFormState {

            val passwordError = updatePasswordError(password)

            val isDataValid = updateIsDateValid(password, passwordError)

            return LoginFormState(passwordError, isDataValid)
        }

        private fun updatePasswordError(password: String?): PasswordError {

            if (password.isNullOrEmpty()) {
                return PasswordError.NONE
            }

            val length = password.length
            if (length < 10) {
                return PasswordError.LENGTH
            }

            return PasswordError.NONE
        }

        private fun updateIsDateValid(
            password: String?,
            passwordError: PasswordError
        ): Boolean {

            if (passwordError != PasswordError.NONE) return false

            return !password.isNullOrEmpty()
        }
    }
}
