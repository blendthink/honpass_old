package dev.honwaka_lab.honpass.data.repositories

import android.database.sqlite.SQLiteConstraintException
import android.util.Log
import dev.honwaka_lab.honpass.convenience.Result
import dev.honwaka_lab.honpass.data.dao.AdminDao
import dev.honwaka_lab.honpass.data.entities.Admin
import dev.honwaka_lab.honpass.utils.HashUtil
import java.util.*

internal class AdminRepository(private val adminDao: AdminDao) {

    suspend fun register(rawPassword: String): Result<Unit> {

        val uniqueId = UUID.randomUUID().toString()

        val password = HashUtil.encode(rawPassword)

        val admin = Admin(guid = uniqueId, password = password)

        return try {

            adminDao.insert(admin)

            Result.Success(Unit)

        } catch (e: SQLiteConstraintException) {

            Result.Error(e)
        }
    }

    suspend fun login(name: String = "default", rawPassword: String) {

        val password = adminDao.find(name)?.password ?: return

        val result = HashUtil.match(rawPassword, password)

        if (result) {

            // TODO: Adminをメモリ上に保存する
            Log.d("ログイン成功", password)

        } else {

            Log.d("ログイン失敗", password)
        }
    }

    suspend fun isRegistered(name: String = "default"): Boolean = adminDao.find(name) != null
}