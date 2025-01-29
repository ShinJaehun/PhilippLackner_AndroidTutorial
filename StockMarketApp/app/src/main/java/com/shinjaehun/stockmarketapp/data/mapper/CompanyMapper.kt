package com.shinjaehun.stockmarketapp.data.mapper

import com.shinjaehun.stockmarketapp.data.local.CompanyListingEntity
import com.shinjaehun.stockmarketapp.data.remote.dto.CompanyInfoDto
import com.shinjaehun.stockmarketapp.domain.model.CompanyInfo
import com.shinjaehun.stockmarketapp.domain.model.CompanyListing

fun CompanyListingEntity.toCompanyListing(): CompanyListing {
    return CompanyListing(
        name = name,
        symbol = symbol,
        exchange = exchange
    )
}

fun CompanyListing.toCompanyListingEntity(): CompanyListingEntity {
    return CompanyListingEntity(
        name = name,
        symbol = symbol,
        exchange = exchange
    )
}

fun CompanyInfoDto.toCompanyInfo(): CompanyInfo {
    return CompanyInfo(
        // 정확하지는 않은데
        // api quota를 다 소모한 상황에서 api server가 응답하지 않는다면?
        // nullable로 처리하는 게 맞다?
        symbol = symbol ?: "",
        description = description ?: "",
        name = name ?: "",
        country = country ?: "",
        industry = industry ?: ""
    )
}