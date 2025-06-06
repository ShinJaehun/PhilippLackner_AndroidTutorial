package com.shinjaehun.cryptocurrencyapp.domain.model

data class CoinDetail(
    val coinId: String,
    val name: String,
    val description: String,
    val symbol: String,
    val rank: Int,
    val isActive: Boolean,
    val tags: List<String>,
    val team: List<TeamMember> //이게 data를 참조하는 건 위반 아닐까???
)
