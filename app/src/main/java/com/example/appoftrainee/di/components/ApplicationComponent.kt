package com.example.appoftrainee.di.components

import android.content.Context
import com.example.appoftrainee.di.modules.LocalDatabaseModule
import com.example.appoftrainee.di.scopes.ApplicationScope
import com.example.appoftrainee.di.modules.NetworkModule
import com.example.appoftrainee.di.modules.RandomUserApiModule
import com.example.appoftrainee.ui.screens.home_screen.HomeScreenViewModel
import dagger.BindsInstance
import dagger.Component


@ApplicationScope
@Component(
    modules = [
        NetworkModule::class,
        RandomUserApiModule::class,
        LocalDatabaseModule::class,
    ]
)
interface ApplicationComponent {

    fun inject(homeScreenViewModel: HomeScreenViewModel)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): ApplicationComponent
    }
}