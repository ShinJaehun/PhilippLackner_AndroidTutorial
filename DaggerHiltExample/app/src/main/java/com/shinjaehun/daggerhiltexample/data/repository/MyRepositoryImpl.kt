package com.shinjaehun.daggerhiltexample.data.repository

import android.app.Application
import com.shinjaehun.daggerhiltexample.R
import com.shinjaehun.daggerhiltexample.data.remote.MyApi
import com.shinjaehun.daggerhiltexample.domain.repository.MyRepository
import javax.inject.Inject

class MyRepositoryImpl @Inject constructor(
    private val api: MyApi,
    private val appContext: Application
): MyRepository {
    init {
        val appName = appContext.getString(R.string.app_name)
        println("Hello from the repository. The app name is $appName")
    }

    override suspend fun doNetworkCall() {

    }
}