package com.example.appoftrainee.domain.interactors

import com.example.appoftrainee.data.db.entities.UserProfile
import com.example.appoftrainee.data.random_user_api.RandomUserApi
import com.example.appoftrainee.domain.converters.UserConverter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class RandomUserApiInteractor @Inject constructor(private val randomUserApi: RandomUserApi) {

    fun getUsersFromApi(count: Int = DEFAULT_USERS_COUNT): Flow<List<UserProfile>> = flow {
        emit(randomUserApi.getUsers(count))
    }.map { resultDto ->
            UserConverter.convertRandomUserApiDtoListToUserProfileList(resultDto.users)
        }.flowOn(Dispatchers.IO)


    companion object {
        const val DEFAULT_USERS_COUNT = 32
    }
}