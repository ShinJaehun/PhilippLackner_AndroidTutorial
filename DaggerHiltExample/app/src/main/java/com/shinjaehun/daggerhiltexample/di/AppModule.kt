package com.shinjaehun.daggerhiltexample.di

import android.app.Application
import com.shinjaehun.daggerhiltexample.data.remote.MyApi
import com.shinjaehun.daggerhiltexample.data.repository.MyRepositoryImpl
import com.shinjaehun.daggerhiltexample.domain.repository.MyRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class) // singleton: lifetime
object AppModule {

    @Provides
    @Singleton // singleton scope, 이게 없으면 repositoryimpl마다 이걸 생성하게 됨
    fun provideMyApi(): MyApi {
        return Retrofit.Builder()
            .baseUrl("https://www.naver.com")
            .build()
            .create(MyApi::class.java)
    }

//    @Provides
//    @Singleton
//    fun provideMyRepository(api: MyApi, app: Application, @Named("hello1") hello1: String): MyRepository {
//        return MyRepositoryImpl(api, app)
//    }

    @Provides
    @Singleton
    @Named("hello1")
    fun provideString1() = "Hello 1"

    @Provides
    @Singleton
    @Named("hello2")
    fun provideString2() = "Hello 2"
}