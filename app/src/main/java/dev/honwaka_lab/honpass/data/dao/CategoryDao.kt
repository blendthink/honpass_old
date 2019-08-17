package dev.honwaka_lab.honpass.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import dev.honwaka_lab.honpass.data.entities.Category

@Dao
internal interface CategoryDao {

    @Insert
    suspend fun insert(category: Category)

    @Query("SELECT * FROM categories ORDER BY name ASC")
    suspend fun getAll(): LiveData<List<Category>>

    @Update
    suspend fun update(category: Category)

    @Delete
    suspend fun delete(category: Category)
}