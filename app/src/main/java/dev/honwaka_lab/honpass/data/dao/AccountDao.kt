package dev.honwaka_lab.honpass.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import dev.honwaka_lab.honpass.data.entities.Account

@Dao
internal interface AccountDao {

    @Insert
    suspend fun insert(account: Account)

    @Query("SELECT * FROM accounts ORDER BY name ASC")
    suspend fun getAll(): LiveData<List<Account>>

    @Update
    suspend fun update(account: Account)

    @Delete
    suspend fun delete(account: Account)
}