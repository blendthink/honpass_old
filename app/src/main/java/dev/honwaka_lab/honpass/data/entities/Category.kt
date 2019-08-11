package dev.honwaka_lab.honpass.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "categories",
    indices = [
        Index(name = "index_categories_parent_id", value = ["parent_id"])
    ]
)
internal data class Category(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    @ColumnInfo(name = "parent_id")
    val parentId: Int?
) {

    init {

        if (id < 0) {
            throw RuntimeException("")
        }

        if (name.isEmpty() || name.length > 255) {
            throw RuntimeException("")
        }

        if (parentId != null && parentId <= 0) {
            throw RuntimeException("")
        }
    }
}