package com.shinjaehun.jetpackcomposepokedex.util


sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
//    class Error<T>(data: T? = null, message: String) : Resource<T>(data, message) // 이렇게 하면 오류 발생!
    class Loading<T>(data: T? = null) : Resource<T>(data) // viewmodel에서 loading을 handling하겠데요... 그래서 여기선 쓸모 없다고
}