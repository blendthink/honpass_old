package dev.honwaka_lab.honpass.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import dev.honwaka_lab.honpass.data.entities.Category

@Dao
internal interface CategoryDao {

    @Insert
    suspend fun insert(category: Category)

    @Query("SELECT * FROM categories ORDER BY name ASC")
    suspend fun getAll(): List<Category>

    @Update
    suspend fun update(category: Category)

    @Delete
    suspend fun delete(category: Category)
}
