package com.shinjaehun.daggerhiltexample.di

import com.shinjaehun.daggerhiltexample.data.repository.MyRepositoryImpl
import com.shinjaehun.daggerhiltexample.domain.repository.MyRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindMyRepository(
        myRepositoryImpl: MyRepositoryImpl
    ): MyRepository
}