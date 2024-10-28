package com.shinjaehun.cryptotracker.di

import com.shinjaehun.cryptotracker.core.data.networking.HttpClientFactory
import com.shinjaehun.cryptotracker.crypto.data.networking.RemoteCoinDataSource
import com.shinjaehun.cryptotracker.crypto.domain.CoinDataSource
import io.ktor.client.engine.cio.CIO
import org.koin.androidx.viewmodel.dsl.viewModelOf
import com.shinjaehun.cryptotracker.crypto.presentation.coin_list.CoinListViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    single { HttpClientFactory.create(CIO.create()) }
//    single { RemoteCoinDataSource(get()) } // 비슷한 건가봐...
    singleOf(::RemoteCoinDataSource).bind<CoinDataSource>()

    viewModelOf(::CoinListViewModel)
    // import com.shinjaehun.cryptotracker.crypto.presentation.coin_list.CoinListViewModel 이거 해줘야 정상 동작함!

}