package dev.honwaka_lab.honpass.data.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "admins",
    indices = [
        Index(name = "index_admins_name_password", value = ["name"], unique = true)
    ]
)
internal data class Admin(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val guid: String,
    val name: String = "default",
    val password: String
) {

    init {

        if (id < 0) {
            throw RuntimeException("")
        }

        if (guid.length != 36) {
            throw RuntimeException("")
        }

        if (name.isEmpty() || name.length > 255) {
            throw RuntimeException("")
        }

        if (password.length != 60) {
            throw RuntimeException("")
        }
    }
}
