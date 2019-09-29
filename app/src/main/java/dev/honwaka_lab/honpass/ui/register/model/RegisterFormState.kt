package dev.honwaka_lab.honpass.ui.register.model

internal data class RegisterFormState(
    val passwordError: PasswordError = PasswordError.NONE,
    val passwordForConfirmError: PasswordForConfirmError = PasswordForConfirmError.NONE,
    val isDataValid: Boolean = false
) {

    companion object {

        // TODO 後々、init()で判別してインスタンス化するように修正する
        fun newInstance(password: String?, passwordForConfirm: String?): RegisterFormState {

            val passwordError = updatePasswordError(password)

            val passwordForConfirmError = updatePasswordForConfirmError(
                password, passwordForConfirm, passwordError
            )

            val isDataValid = updateIsDataValid(
                password, passwordForConfirm, passwordError, passwordForConfirmError
            )

            return RegisterFormState(passwordError, passwordForConfirmError, isDataValid)
        }

        private const val PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!-/:-@\\[-`{-~])[!-~]{10,36}$"

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
            passwordForConfirm: String?,
            passwordError: PasswordError
        ): PasswordForConfirmError {

            if (passwordForConfirm.isNullOrEmpty()) {
                return PasswordForConfirmError.NONE
            }

            if (passwordError != PasswordError.NONE) {
                return PasswordForConfirmError.PASSWORD_ERROR
            }

            return if (password == passwordForConfirm) {
                PasswordForConfirmError.NONE
            } else {
                PasswordForConfirmError.DIFFERENT
            }
        }

        private fun updateIsDataValid(
            password: String?,
            passwordForConfirm: String?,
            passwordError: PasswordError,
            passwordForConfirmError: PasswordForConfirmError
        ): Boolean {

            if (passwordError != PasswordError.NONE) return false

            if (passwordForConfirmError != PasswordForConfirmError.NONE) return false

            return !password.isNullOrEmpty() && !passwordForConfirm.isNullOrEmpty()
        }
    }
}