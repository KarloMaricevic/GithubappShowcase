package com.example.githubapp.core.di

import com.example.githubapp.core.navigation.Navigator
import com.example.githubapp.core.navigation.NavigatorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NavigationModule {

    @Binds
    @Singleton
    abstract fun bindNavigator(navigatorImpl: NavigatorImpl): Navigator
}
