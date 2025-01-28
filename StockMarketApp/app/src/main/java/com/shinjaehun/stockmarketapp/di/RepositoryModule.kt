package com.shinjaehun.stockmarketapp.di

import com.shinjaehun.stockmarketapp.data.csv.CSVParser
import com.shinjaehun.stockmarketapp.data.csv.CompanyListingsParser
import com.shinjaehun.stockmarketapp.data.repository.StockRepositoryImpl
import com.shinjaehun.stockmarketapp.domain.model.CompanyListing
import com.shinjaehun.stockmarketapp.domain.repository.StockRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCompanyListingsParser(
        companyListingsParser: CompanyListingsParser
    ): CSVParser<CompanyListing>

    @Binds
    @Singleton
    abstract fun bindStockRepository(
        stockRepositoryImpl: StockRepositoryImpl
    ): StockRepository
}