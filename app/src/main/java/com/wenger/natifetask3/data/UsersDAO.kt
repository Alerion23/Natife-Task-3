package com.wenger.natifetask3.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UsersDAO {

    @Insert
    suspend fun uploadUsers(users: List<User>)

    @Query("SELECT * FROM User WHERE uuid = :uuid")
    suspend fun getUserById(uuid: String?): User

    @Query("SELECT * FROM User")
    suspend fun getAll() : List<User>
}