package dev.honwaka_lab.honpass.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import dev.honwaka_lab.honpass.data.entities.Account
import io.reactivex.Completable

@Dao
internal interface AccountDao {

    @Query("SELECT * FROM accounts ORDER BY name ASC")
    suspend fun getAll(): LiveData<List<Account>>

    @Insert
    suspend fun insert(account: Account): Completable

    @Delete
    suspend fun delete(account: Account): Completable
}