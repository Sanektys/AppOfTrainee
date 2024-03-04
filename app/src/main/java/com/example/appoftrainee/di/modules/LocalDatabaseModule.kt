package com.example.appoftrainee.di.modules

import android.content.Context
import androidx.room.Room
import com.example.appoftrainee.data.db.dao.UserDao
import com.example.appoftrainee.data.db.databases.UsersDatabase
import com.example.appoftrainee.di.scopes.ApplicationScope
import dagger.Module
import dagger.Provides


@Module
class LocalDatabaseModule {

    @[Provides ApplicationScope]
    fun provideUsersDatabase(context: Context): UsersDatabase = Room
        .databaseBuilder(context, UsersDatabase::class.java, UsersDatabase.DATABASE_NAME)
        .build()

    @Provides
    fun provideUserDao(usersDatabase: UsersDatabase): UserDao = usersDatabase.getUserDao()
}