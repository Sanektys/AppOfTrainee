package com.example.appoftrainee.domain.interactors

import com.example.appoftrainee.data.User
import com.example.appoftrainee.data.db.entities.UserProfile
import com.example.appoftrainee.data.repositories.LocalRepositoryImpl
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class LocalDbInteractor @Inject constructor(private val localRepositoryImpl: LocalRepositoryImpl) {

    fun getLocalDbObserver(): Flow<List<User>> = localRepositoryImpl.getAllUsers()

    suspend fun changeListOfUsers(newUsers: List<UserProfile>)
            = localRepositoryImpl.changeListOfUsers(newUsers)
}