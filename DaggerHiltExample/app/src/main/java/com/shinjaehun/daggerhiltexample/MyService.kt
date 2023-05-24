package com.shinjaehun.daggerhiltexample

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.shinjaehun.daggerhiltexample.domain.repository.MyRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


//@AndroidEntryPoint
// 이렇게 쓸 수 없음
//class MyService @Inject constructor(
//    private val repository: MyRepository
//): Service() {
//}
@AndroidEntryPoint
class MyService: Service() {
    @Inject
    lateinit var repository: MyRepository

    override fun onCreate() {
        super.onCreate()
//        repository.doNetworkCall()
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }
}