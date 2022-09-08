package com.wenger.natifetask3.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UsersDAO {

    @Insert
    fun uploadUsers(users: List<User>)

    @Query("SELECT * FROM User WHERE uuid = :uuid")
    suspend fun getUserById(uuid: String?): User

    @Query("SELECT * FROM User")
    fun getAll(): List<User>
}