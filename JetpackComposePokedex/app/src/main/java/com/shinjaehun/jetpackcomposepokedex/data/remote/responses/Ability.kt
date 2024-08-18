package com.shinjaehun.jetpackcomposepokedex.data.remote.responses

data class Ability(
    val ability: AbilityX,
    val is_hidden: Boolean,
    val slot: Int
)