package com.example.freshstart.di

import com.example.freshstart.navigation.AppNavigator
import com.example.freshstart.navigation.IAppNavigator
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun bindAppNavigator(appNavigator: AppNavigator): IAppNavigator
}
