package com.wenger.natifetask3.data

import android.content.Context
import androidx.room.Room

class DatabaseWorker(private val context: Context) {

    private var mDatabase: UsersDatabase? = null

    private val database: UsersDatabase
        get() {
            if (mDatabase == null) {
                val databaseBuilder = Room.databaseBuilder(
                    context.applicationContext,
                    UsersDatabase::class.java,
                    "user database"
                ).build()
                mDatabase = databaseBuilder
            }
            return mDatabase!!
        }

    fun getDatabaseClient(): UsersDatabase = database
}