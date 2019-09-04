package dev.honwaka_lab.honpass.ui.register.model

internal enum class PasswordError(val message: String?) {
    NONE(null),
    LENGTH("※ 10桁以上"),
    TYPE("※ 半角英大文字+小文字+数字+記号混じり")
}