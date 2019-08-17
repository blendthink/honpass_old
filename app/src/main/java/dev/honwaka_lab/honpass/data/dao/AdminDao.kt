package dev.honwaka_lab.honpass.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import dev.honwaka_lab.honpass.data.entities.Admin
import io.reactivex.Completable
import io.reactivex.Single

@Dao
internal interface AdminDao {

    @Insert
    suspend fun insert(admin: Admin): Completable

    @Query("SELECT * FROM admins WHERE name = :name AND password = :password")
    suspend fun find(name: String, password: String): Single<Admin>
}