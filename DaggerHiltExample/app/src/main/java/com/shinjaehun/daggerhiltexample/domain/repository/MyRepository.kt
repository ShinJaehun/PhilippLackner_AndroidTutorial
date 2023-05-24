package com.shinjaehun.daggerhiltexample.domain.repository

interface MyRepository {
    suspend fun doNetworkCall()
}