package dev.honwaka_lab.honpass.data.repositories

import android.util.Log
import dev.honwaka_lab.honpass.data.dao.AdminDao
import dev.honwaka_lab.honpass.data.entities.Admin
import dev.honwaka_lab.honpass.utils.HashUtil
import java.util.*

internal class AdminRepository(private val adminDao: AdminDao) {

    suspend fun register(rawPassword: String) {

        val uniqueId = UUID.randomUUID().toString()

        val password = HashUtil.encode(rawPassword)

        val admin = Admin(guid = uniqueId, password = password)

        adminDao.insert(admin)
    }

    suspend fun login(name: String = "default", rawPassword: String) {

        val admin = adminDao.find(name)

        val password = HashUtil.encode(rawPassword)

        val result = HashUtil.match(rawPassword, password)

        if (result) {

            // TODO: Adminをメモリ上に保存する
            Log.d("ログイン成功", admin.password)

        } else {

            Log.d("ログイン失敗", admin.password)
        }

    }
}