package com.example.roomdb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "friends")
data class FriendModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var friendName: String,
    var latitude: Double,
    var longitude: Double)