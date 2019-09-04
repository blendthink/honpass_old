package dev.honwaka_lab.honpass.ui.register.model

internal enum class PasswordForConfirmError(val message: String?) {
    NONE(null),
    DIFFERENT("※ パスワードの不一致")
}