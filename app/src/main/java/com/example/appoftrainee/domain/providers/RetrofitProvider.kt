package com.example.appoftrainee.domain.providers

import com.example.appoftrainee.data.random_user_api.RandomUserApiConstants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitProvider {

    fun create(client: OkHttpClient): Retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl(RandomUserApiConstants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}