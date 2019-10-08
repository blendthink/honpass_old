package dev.honwaka_lab.honpass.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "accounts",
    indices = [
        Index(name = "index_accounts_admin_id", value = ["admin_id"]),
        Index(name = "index_accounts_category_id", value = ["category_id"])
    ],
    foreignKeys = [
        ForeignKey(
            entity = Admin::class,
            parentColumns = ["id"],
            childColumns = ["admin_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Category::class,
            parentColumns = ["id"],
            childColumns = ["category_id"],
            onDelete = ForeignKey.NO_ACTION
        )
    ]
)
internal data class Account(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "admin_id")
    val adminId: Int,
    @ColumnInfo(name = "category_id")
    val categoryId: Int?,
    val name: String,
    @ColumnInfo(name = "login_id")
    val loginId: String,
    @ColumnInfo(name = "login_password")
    val loginPassword: String
) {

    init {

        if (id < 0) {
            throw RuntimeException("")
        }

        if (adminId <= 0) {
            throw RuntimeException("")
        }

        if (categoryId != null && categoryId <= 0) {
            throw RuntimeException("")
        }

        if (name.isEmpty() || name.length > 255) {
            throw RuntimeException("")
        }

        if (loginId.isEmpty() || loginId.length > 255) {
            throw RuntimeException("")
        }

        if (loginPassword.isEmpty() || loginPassword.length > 255) {
            throw RuntimeException("")
        }
    }
}
