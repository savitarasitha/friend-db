package com.example.roomdb

import android.app.Application
import androidx.room.Room

class AppActivity: Application() {
    companion object {
        var db: MyDatabase? = null
        fun getDatabase(): MyDatabase? {
            return db
        }

    }
    override fun onCreate() {
        super.onCreate()
            db= Room.databaseBuilder(applicationContext, MyDatabase::class.java,"friend.db").allowMainThreadQueries().build()
    }

}