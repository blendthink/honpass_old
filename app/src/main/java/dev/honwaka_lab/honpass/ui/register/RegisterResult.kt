package dev.honwaka_lab.honpass.ui.register

import dev.honwaka_lab.honpass.data.entities.Admin

internal data class RegisterResult(
    val success: Admin? = null,
    val error: Int? = null
)