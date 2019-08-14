package dev.honwaka_lab.honpass.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import dev.honwaka_lab.honpass.data.entities.Category
import io.reactivex.Completable

@Dao
internal interface CategoryDao {

    @Query("SELECT * FROM categories ORDER BY name ASC")
    suspend fun getAll(): LiveData<List<Category>>

    @Insert
    suspend fun insert(category: Category): Completable

    @Delete
    suspend fun delete(category: Category): Completable
}