package com.shinjaehun.cryptocurrencyapp.data.remote.dto

import com.google.gson.annotations.SerializedName

//data class CoinDetailDto(
//    val description: String,
//    val development_status: String,
//    val first_data_at: String,
//    val hardware_wallet: Boolean,
//    val hash_algorithm: String,
//    val id: String,
//    val is_active: Boolean,
//    val is_new: Boolean,
//    val last_data_at: String,
//    val links: Links,
//    val links_extended: List<LinksExtended>,
//    val logo: String,
//    val message: String,
//    val name: String,
//    val open_source: Boolean,
//    val org_structure: String,
//    val proof_type: String,
//    val rank: Int,
//    val started_at: String,
//    val symbol: String,
//    val tags: List<Tag>,
//    val team: List<Team>,
//    val type: String,
//    val whitepaper: Whitepaper
//)


data class CoinDetailDto(
    val description: String,
    @SerializedName("development_status")
    val developmentStatus: String,
    @SerializedName("first_data_at")
    val firstDataAt: String,
    @SerializedName("hardware_wallet")
    val hardwareWallet: Boolean,
    @SerializedName("hash_algorithm")
    val hashAlgorithm: String,
    val id: String,
    @SerializedName("is_active")
    val isActive: Boolean,
    @SerializedName("is_new")
    val isNew: Boolean,
    @SerializedName("last_data_at")
    val lastDataAt: String,
    val links: Links,
    @SerializedName("links_extended")
    val linksExtended: List<LinksExtended>,
    val message: String,
    val name: String,
    @SerializedName("open_source")
    val openSource: Boolean,
    @SerializedName("org_structure")
    val orgStructure: String,
    @SerializedName("proof_type")
    val proofType: String,
    val rank: Int,
    @SerializedName("started_at")
    val startedAt: String,
    val symbol: String,
    val tags: List<Tag>,
    val team: List<TeamMemberDto>,
    val type: String,
    val whitepaper: Whitepaper
)