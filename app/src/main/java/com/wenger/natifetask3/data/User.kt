package com.wenger.natifetask3.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "User")
@Parcelize
data class User(
    @PrimaryKey
    @ColumnInfo(name = "uuid")
    var uuid: String,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "last name")
    var lastName: String,
    @ColumnInfo(name = "photo")
    var userPhoto: String
) : Parcelable