package dev.honwaka_lab.honpass.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import dev.honwaka_lab.honpass.data.entities.Account

@Dao
internal interface AccountDao {

    @Insert
    suspend fun insert(account: Account)

    @Query("SELECT * FROM accounts ORDER BY name ASC")
    suspend fun getAll(): List<Account>

    @Update
    suspend fun update(account: Account)

    @Delete
    suspend fun delete(account: Account)
}
