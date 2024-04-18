package com.example.githubapp.core.di

import com.example.githubapp.core.dictionary.Dictionary
import com.example.githubapp.core.dictionary.DictionaryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface DictionaryModule {

    @Binds
    fun bindDictionary(dictionaryImpl: DictionaryImpl): Dictionary
}
