package com.shinjaehun.ktortictactoe.data

import kotlinx.serialization.Serializable

@Serializable
data class MakeTurn (
    val x: Int,
    val y: Int
)