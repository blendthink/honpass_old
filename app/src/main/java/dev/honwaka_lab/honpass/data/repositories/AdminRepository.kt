package dev.honwaka_lab.honpass.data.repositories

import dev.honwaka_lab.honpass.data.dao.AdminDao
import dev.honwaka_lab.honpass.data.entities.Admin
import java.util.*

internal class AdminRepository(private val adminDao: AdminDao) {

    suspend fun register(password: String) {

        val uniqueId = UUID.randomUUID().toString()

        val admin = Admin(guid = uniqueId, password = password)

        adminDao.insert(admin)
    }

    suspend fun login(name: String, password: String) {

        adminDao.find(name, password)

        // TODO: Adminをメモリ上に保存する
    }
}