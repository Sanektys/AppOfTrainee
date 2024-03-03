package com.example.appoftrainee.di.modules

import com.example.appoftrainee.di.scopes.ApplicationScope
import com.example.appoftrainee.domain.providers.OkHttpProvider
import com.example.appoftrainee.domain.providers.RetrofitProvider
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit


@Module
class NetworkModule {

    @Provides
    fun provideOkHttpClient(): OkHttpClient = OkHttpProvider.create()

    @[Provides ApplicationScope]
    fun provideRetrofitService(client: OkHttpClient): Retrofit = RetrofitProvider.create(client)
}