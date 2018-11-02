package com.example.hungnb.basicroomrxjavainkotlin.ui

import androidx.lifecycle.ViewModel
import com.example.hungnb.basicroomrxjavainkotlin.persistence.User
import com.example.hungnb.basicroomrxjavainkotlin.persistence.UserDao
import io.reactivex.Completable
import io.reactivex.Flowable

class UserViewModel(private val datasouce: UserDao) : ViewModel() {
    companion object {
        // using a hardcoded value for simplicity
        const val USER_ID = "1"
    }

    // for every emission of the user, get the user name
    fun userName(): Flowable<String> {
        return datasouce.getUserById(USER_ID)
            .map { user -> user.userName }
    }

    fun updateUserName(userName: String): Completable {
        return Completable.fromAction {
            val user = User(USER_ID, userName)
            datasouce.insertUser(user)
        }
    }
}