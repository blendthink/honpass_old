package dev.honwaka_lab.honpass.ui.register.model

internal enum class PasswordError(val message: String?) {
    NONE(null),
    LENGTH("※ 10桁以上にしてください。"),
    ZENKAKU("※ 半角文字で入力してください。"),
    TYPE("※ 英文字・数字・記号を最低1文字ずつ含めてください。")
}



internal class PasswordForm(
    val password: String?
) {

    var passwordError = PasswordError.NONE

    init {

        val length = password?.length ?: 0


    }

    private fun passwordError(): PasswordError {

        val length = password?.length ?: 0
        if (length < 10) {
            return PasswordError.LENGTH
        }

        return PasswordError.NONE
    }
}