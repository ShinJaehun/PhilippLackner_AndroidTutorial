package com.shinjaehun.stockmarketapp.domain.repository

import com.shinjaehun.stockmarketapp.domain.model.CompanyListing
import com.shinjaehun.stockmarketapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface StockRepository {

    suspend fun getCompanyListings(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<CompanyListing>>>
}