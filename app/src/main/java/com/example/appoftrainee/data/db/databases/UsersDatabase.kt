package com.example.appoftrainee.data.db.databases

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.appoftrainee.data.db.dao.UserDao
import com.example.appoftrainee.data.db.entities.UserProfile


@Database(entities = [UserProfile::class], version = 1, exportSchema = true)
abstract class UsersDatabase : RoomDatabase() {

    abstract fun getUserDao(): UserDao


    companion object {
        const val DATABASE_NAME = "users_db"
    }
}