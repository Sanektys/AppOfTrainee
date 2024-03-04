package com.example.appoftrainee.domain.interactors

import com.example.appoftrainee.data.db.entities.UserProfile
import com.example.appoftrainee.data.repositories.LocalRepositoryImpl
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class LocalDbInteractor @Inject constructor(private val localRepositoryImpl: LocalRepositoryImpl) {

    fun getLocalDbObserver(): Flow<List<UserProfile>> = localRepositoryImpl.getAllUsers()
}