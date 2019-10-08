package dev.honwaka_lab.honpass.ui.login.model

internal enum class PasswordError(val message: String?) {
    NONE(null),
    LENGTH("※ 10桁以上")
}
