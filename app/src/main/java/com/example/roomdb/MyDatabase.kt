package com.example.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FriendModel::class], version = 1)
abstract class MyDatabase : RoomDatabase() {

    abstract fun myDao(): MyDao

    companion object {
        var INSTANCE: MyDatabase? = null
        const val DATABASE_NAME = "friend.db"
        fun getInstance(context: Context): MyDatabase? {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        MyDatabase::class.java,
                        DATABASE_NAME
                    )
                        .createFromAsset(DATABASE_NAME)
                        .build()
                }
            }
            return INSTANCE
        }
    }
}