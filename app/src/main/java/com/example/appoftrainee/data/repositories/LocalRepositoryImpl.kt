package com.example.appoftrainee.data.repositories

import com.example.appoftrainee.data.db.dao.UserDao
import com.example.appoftrainee.data.db.entities.UserProfile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject


class LocalRepositoryImpl @Inject constructor(private val userDao: UserDao) : LocalRepository {

    override fun getAllUsers(): Flow<List<UserProfile>>
            = userDao.getAllUsers().flowOn(Dispatchers.IO)

    override suspend fun addUsers(list: List<UserProfile>) = withContext(Dispatchers.IO) {
        userDao.insertUsers(list)
    }

    override suspend fun deleteAllUsers() = withContext(Dispatchers.IO) {
        userDao.deleteAllUsers()
    }

    override suspend fun changeListOfUsers(newList: List<UserProfile>) = withContext(Dispatchers.IO) {
        userDao.deleteAllUsersAndPutNew(newList)
    }
}