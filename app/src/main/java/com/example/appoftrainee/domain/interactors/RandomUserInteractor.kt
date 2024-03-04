package com.example.appoftrainee.domain.interactors

import com.example.appoftrainee.data.random_user_api.RandomUserApi
import com.example.appoftrainee.data.repositories.LocalRepository
import com.example.appoftrainee.domain.converters.UserConverter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class RandomUserInteractor @Inject constructor(
    private val localRepository: LocalRepository,
    private val randomUserApi: RandomUserApi
) {

    suspend fun getUsersFromApi(count: Int = DEFAULT_USERS_COUNT) = withContext(Dispatchers.IO) {
        val resultDto = randomUserApi.getUsers(count)
        val newUsers = UserConverter.convertRandomUserApiDtoListToUserProfileList(resultDto.users)
        localRepository.changeListOfUsers(newUsers)
    }


    companion object {
        const val DEFAULT_USERS_COUNT = 32
    }
}