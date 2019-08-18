package dev.honwaka_lab.honpass.data

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.honwaka_lab.honpass.data.dao.AccountDao
import dev.honwaka_lab.honpass.data.dao.AdminDao
import dev.honwaka_lab.honpass.data.dao.CategoryDao
import dev.honwaka_lab.honpass.data.entities.Account
import dev.honwaka_lab.honpass.data.entities.Admin
import dev.honwaka_lab.honpass.data.entities.Category

@Database(
    entities = [
        Account::class,
        Admin::class,
        Category::class
    ],
    version = 1,
    exportSchema = false
)
internal abstract class HonpassDatabase : RoomDatabase() {

    abstract fun accountDao(): AccountDao

    abstract fun adminDao(): AdminDao

    abstract fun categoryDao(): CategoryDao
}