package com.example.hungnb.basicroomrxjavainkotlin

import android.content.Context
import com.example.hungnb.basicroomrxjavainkotlin.persistence.UserDao
import com.example.hungnb.basicroomrxjavainkotlin.persistence.UsersDatabase
import com.example.hungnb.basicroomrxjavainkotlin.ui.ViewModelFactory

object InjectionApp {
    fun provideUserDataSource(context: Context): UserDao {
        val database = UsersDatabase.getInstance(context)
        return database.userDao()
    }

    fun provideViewModelFactory(context: Context): ViewModelFactory {
        val dataSource = provideUserDataSource(context)
        return ViewModelFactory(dataSource)
    }
}