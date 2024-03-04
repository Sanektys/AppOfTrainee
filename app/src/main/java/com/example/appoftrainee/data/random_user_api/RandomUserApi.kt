package com.example.appoftrainee.data.random_user_api

import com.example.appoftrainee.data.random_user_api.Dto.RandomUserResultDto
import retrofit2.http.GET
import retrofit2.http.Query


interface RandomUserApi {

    @GET("/?exc=login,nat,registered")
    suspend fun getUsers(
        @Query("results") numberOfUsers: Int
    ): RandomUserResultDto
}