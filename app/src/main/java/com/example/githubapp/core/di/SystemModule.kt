package com.example.githubapp.core.di

import com.example.githubapp.core.system.SystemCall
import com.example.githubapp.core.system.SystemCallImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface SystemModule {

    @Binds
    @Singleton
    fun bindNavigator(systemCallImpl: SystemCallImpl): SystemCall
}
