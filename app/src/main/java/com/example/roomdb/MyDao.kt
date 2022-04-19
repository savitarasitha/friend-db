package com.example.roomdb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MyDao {

    @Insert
     suspend fun addFriend(friendModel: FriendModel?)

    @Query("SELECT * FROM friends")
     fun getActor(): LiveData<List<FriendModel>>

    @Query("DELETE from friends WHERE id IN (:friend_id)")
    suspend fun deleteFriend(friend_id: Int)
}