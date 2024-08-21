package com.shinjaehun.ktorclientandroid.data.remote

object HttpRoutes {

    private const val BASE_URL = "http://192.168.200.36:3000"
//    private const val BASE_URL = "https://jsonplaceholder.typicode.com"
    const val POSTS = "$BASE_URL/posts.json"

    const val SIGN_IN = "$BASE_URL/users/tokens/sign_in"
}