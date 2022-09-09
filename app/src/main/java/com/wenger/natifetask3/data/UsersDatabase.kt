package com.wenger.natifetask3.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UsersDatabase : RoomDatabase() {

    abstract fun usersDao(): UsersDAO

    companion object {

        @Volatile
        private var instance: UsersDatabase? = null

        fun getDatabaseClient(context: Context): UsersDatabase {
            if (instance != null) {
                return instance!!
            }
            synchronized(this) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    UsersDatabase::class.java,
                    "user database"
                ).allowMainThreadQueries().build()
                return instance!!
            }
        }
    }
}