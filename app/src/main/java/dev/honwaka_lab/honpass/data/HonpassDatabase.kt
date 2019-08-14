package dev.honwaka_lab.honpass.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
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
    version = 1
)
internal abstract class HonpassDatabase : RoomDatabase() {

    abstract fun accountDao(): AccountDao

    abstract fun adminDao(): AdminDao

    abstract fun categoryDao(): CategoryDao

    companion object {

        @Volatile
        private var INSTANCE: HonpassDatabase? = null

        fun getInstance(context: Context): HonpassDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                HonpassDatabase::class.java,
                "honpass.db"
            ).build()
    }
}