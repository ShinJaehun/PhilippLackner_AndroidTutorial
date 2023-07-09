package com.shinjaehun.currencyconverter.data

import com.shinjaehun.currencyconverter.BuildConfig
import com.shinjaehun.currencyconverter.data.models.CurrencyResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

//val key = BuildConfig.CURRENCY_API_KEY
interface CurrencyApi {

    @GET("/latest")
    suspend fun getRates(
        @Query("access_key") key: String,
//        @Query("base") base: String
    // base는 하루에 네번만 사용할 수 있다고????
    // this is bad,api is not free!also  &base  only use four on day
    ): Response<CurrencyResponse>

    // 결국은 retrofit에서 key 값을 제대로 전달하지 못하고...
    // 중간에 들어간 @Query("base")  뭐 얘가 URL을 이상하게 바꿔놔서 제대로 값을 전달하지 못했던 원인이 크다...
    // access_key를 이렇게 query 값으로 주면 문제 해결!

//    @GET("/latest?access_key=key")
//    suspend fun getRates(
//        @Query("base") base: String
//    ): Response<CurrencyResponse>
}