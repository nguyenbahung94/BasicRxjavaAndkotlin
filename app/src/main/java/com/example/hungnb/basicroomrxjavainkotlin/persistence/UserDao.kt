package com.example.hungnb.basicroomrxjavainkotlin.persistence

import androidx.room.Dao
import androidx.room.Query
import io.reactivex.Flowable

@Dao
interface UserDao {
    @Query("SELECT * FROM USERS WHERE userid= :id")
    fun getUserById(id: String): Flowable<User>
}