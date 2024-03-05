package com.example.appoftrainee.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.appoftrainee.data.db.entities.UserProfile
import kotlinx.coroutines.flow.Flow


@Dao
abstract class UserDao {

    @Query("SELECT * FROM ${UserProfile.TABLE_NAME}")
    abstract fun getAllUsers(): Flow<List<UserProfile>>

    @Query("SELECT * " +
            "FROM ${UserProfile.TABLE_NAME} " +
            "WHERE ${UserProfile.COLUMN_ID} LIKE :id")
    abstract suspend fun getUserById(id: String): UserProfile

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun insertUsers(users: List<UserProfile>)

    @Query("DELETE FROM ${UserProfile.TABLE_NAME}")
    abstract suspend fun deleteAllUsers()

    @Transaction
    open suspend fun deleteAllUsersAndPutNew(users: List<UserProfile>) {
        deleteAllUsers()
        insertUsers(users)
    }
}