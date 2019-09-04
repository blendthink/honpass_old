package dev.honwaka_lab.honpass.ui.register.model

internal enum class PasswordForConfirmError(val message: String?) {
    NONE(null),
    PASSWORD_ERROR("※ パスワードの入力謝り"),
    DIFFERENT("※ パスワードの不一致")
}