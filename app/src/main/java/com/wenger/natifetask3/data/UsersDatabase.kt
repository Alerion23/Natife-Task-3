package com.wenger.natifetask3.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1)
abstract class UsersDatabase: RoomDatabase() {

    abstract fun UsersDao(): UsersDAO
}