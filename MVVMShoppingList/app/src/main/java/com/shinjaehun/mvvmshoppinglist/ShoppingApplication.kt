package com.shinjaehun.mvvmshoppinglist

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


//class ShoppingApplication: Application(), KodeinAware {
//    override val kodein: Kodein = Kodein.lazy {
//        import(androidXModule(this@ShoppingApplication))
//        bind() from singleton { ShoppingDatabase(instance()) }
//        bind() from singleton { ShoppingRepository(instance()) }
//        bind() from provider { ShoppingViewModelFactory(instance()) }
//    }
//
//}

@HiltAndroidApp
class ShoppingApplication: Application() {

}