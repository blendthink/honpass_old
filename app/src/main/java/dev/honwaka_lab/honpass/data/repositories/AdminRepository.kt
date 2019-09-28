package dev.honwaka_lab.honpass.data.repositories

import android.database.sqlite.SQLiteConstraintException
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

    suspend fun login(name: String = "default", rawPassword: String): Result<Admin> {

        val admin = try {
            adminDao.find(name)
        } catch (e: SQLiteConstraintException) {
            return Result.Error(e)
        }

        admin ?: return Result.Error(Exception("$name は登録されていません"))

        val result = HashUtil.match(rawPassword, admin.password)

        return if (result) {

            Result.Success(admin)

        } else {

            Result.Error(Exception("$name のパスワードが間違っています"))
        }
    }

    suspend fun isRegistered(name: String = "default"): Result<Unit> {

        val admin = try {
            adminDao.find(name)
        } catch (e: SQLiteConstraintException) {
            return Result.Error(e)
        }

        admin ?: return Result.Error(Exception("$name は登録されていません"))

        return Result.Success(Unit)
    }
}