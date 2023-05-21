package com.shinjaehun.mvvmdictionary.core.util

import androidx.annotation.StringRes

typealias SimpleResource = Resource<Unit>

sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    // 정확히는 모르겠는데 어쨌든 이걸로 데이터를 소스에서 받아온 이후 처리할 때 쓰는 거 같음...
    class Loading<T>(data: T? = null): Resource<T>(data)
    class Success<T>(data: T?): Resource<T>(data)
    class Error<T>(message: String, data: T? = null): Resource<T>(data, message)
}