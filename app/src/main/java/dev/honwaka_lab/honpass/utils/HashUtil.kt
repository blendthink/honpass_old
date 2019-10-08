package dev.honwaka_lab.honpass.utils

import java.security.SecureRandom
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

internal class HashUtil private constructor() {

    companion object {

        fun encode(rawPassword: String): String {
            return passwordEncoder().encode(rawPassword)
        }

        fun match(rawPassword: String, encodedPassword: String): Boolean {
            return passwordEncoder().matches(rawPassword, encodedPassword)
        }

        private fun passwordEncoder(): BCryptPasswordEncoder {

            val secureRandom = SecureRandom.getInstanceStrong()
            secureRandom.nextBytes(SALT.toByteArray())

            return BCryptPasswordEncoder(STRENGTH, secureRandom)
        }

        private const val SALT = "honwakadeveloper"
        private const val STRENGTH = 12
    }
}
