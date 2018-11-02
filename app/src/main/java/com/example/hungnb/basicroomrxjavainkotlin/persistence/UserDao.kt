package com.example.hungnb.basicroomrxjavainkotlin.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Flowable

@Dao
interface UserDao {
    /*
    * Get a user by id
    * @return the user from the table with a specific id
    **/
    @Query("SELECT * FROM USERS WHERE userid= :id")
    fun getUserById(id: String): Flowable<User>

    /*
    * Insert a user in the database.If the user already exits,replace it.
    *  @param user the user to be inserted
    * */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User)

    /*
    * Delete all users
    * */
    @Query("DELETE FROM USERS")
    fun deleteAllUsers()
}