package com.example.appoftrainee.data.repositories

import com.example.appoftrainee.data.db.entities.UserProfile
import kotlinx.coroutines.flow.Flow


interface LocalRepository {

    fun getAllUsers(): Flow<List<UserProfile>>

    suspend fun getUserById(id: String): UserProfile

    suspend fun addUsers(list: List<UserProfile>)

    suspend fun deleteAllUsers()

    suspend fun changeListOfUsers(newList: List<UserProfile>)
}