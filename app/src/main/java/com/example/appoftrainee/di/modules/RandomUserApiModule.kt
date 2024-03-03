package com.example.appoftrainee.di.modules

import com.example.appoftrainee.data.random_user_api.RandomUserApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit


@Module
class RandomUserApiModule {

    @Provides
    fun provideRandomUserApi(retrofit: Retrofit): RandomUserApi
            = retrofit.create(RandomUserApi::class.java)
}