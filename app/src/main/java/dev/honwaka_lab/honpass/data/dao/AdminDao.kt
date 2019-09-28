package dev.honwaka_lab.honpass.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import dev.honwaka_lab.honpass.data.entities.Admin

@Dao
internal interface AdminDao {

    @Insert
    suspend fun insert(admin: Admin)

    @Query("SELECT * FROM admins WHERE name = :name")
    suspend fun find(name: String): Admin?
}