package com.shinjaehun.storageexample

import android.os.Build

inline fun <T> sdk29AndUp(onSdk29: () -> T): T? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        onSdk29()
    } else null
}

//inline fun <T> sdk33AndUp(onSdk33: () -> T): T? {
//    return if (Build.VERSION.SDK_INT >= 33) {
//        onSdk33()
//    } else null
//}