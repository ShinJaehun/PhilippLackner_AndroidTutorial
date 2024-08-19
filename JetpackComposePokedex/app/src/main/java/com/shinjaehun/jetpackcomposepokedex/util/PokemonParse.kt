package com.shinjaehun.jetpackcomposepokedex.util

import androidx.compose.ui.graphics.Color
import com.shinjaehun.jetpackcomposepokedex.data.remote.responses.Stat
import com.shinjaehun.jetpackcomposepokedex.data.remote.responses.Type
import com.shinjaehun.jetpackcomposepokedex.ui.theme.AtkColor
import com.shinjaehun.jetpackcomposepokedex.ui.theme.DefColor
import com.shinjaehun.jetpackcomposepokedex.ui.theme.HPColor
import com.shinjaehun.jetpackcomposepokedex.ui.theme.SpAtkColor
import com.shinjaehun.jetpackcomposepokedex.ui.theme.SpDefColor
import com.shinjaehun.jetpackcomposepokedex.ui.theme.SpdColor
import com.shinjaehun.jetpackcomposepokedex.ui.theme.TypeBug
import com.shinjaehun.jetpackcomposepokedex.ui.theme.TypeDark
import com.shinjaehun.jetpackcomposepokedex.ui.theme.TypeDragon
import com.shinjaehun.jetpackcomposepokedex.ui.theme.TypeElectric
import com.shinjaehun.jetpackcomposepokedex.ui.theme.TypeFairy
import com.shinjaehun.jetpackcomposepokedex.ui.theme.TypeFighting
import com.shinjaehun.jetpackcomposepokedex.ui.theme.TypeFire
import com.shinjaehun.jetpackcomposepokedex.ui.theme.TypeFlying
import com.shinjaehun.jetpackcomposepokedex.ui.theme.TypeGhost
import com.shinjaehun.jetpackcomposepokedex.ui.theme.TypeGrass
import com.shinjaehun.jetpackcomposepokedex.ui.theme.TypeGround
import com.shinjaehun.jetpackcomposepokedex.ui.theme.TypeIce
import com.shinjaehun.jetpackcomposepokedex.ui.theme.TypeNormal
import com.shinjaehun.jetpackcomposepokedex.ui.theme.TypePoison
import com.shinjaehun.jetpackcomposepokedex.ui.theme.TypePsychic
import com.shinjaehun.jetpackcomposepokedex.ui.theme.TypeRock
import com.shinjaehun.jetpackcomposepokedex.ui.theme.TypeSteel
import com.shinjaehun.jetpackcomposepokedex.ui.theme.TypeWater
import java.util.Locale

fun parseTypeToColor(type: Type): Color {
    return when(type.type.name.toLowerCase(Locale.ROOT)) {
        "normal" -> TypeNormal
        "fire" -> TypeFire
        "water" -> TypeWater
        "electric" -> TypeElectric
        "grass" -> TypeGrass
        "ice" -> TypeIce
        "fighting" -> TypeFighting
        "poison" -> TypePoison
        "ground" -> TypeGround
        "flying" -> TypeFlying
        "psychic" -> TypePsychic
        "bug" -> TypeBug
        "rock" -> TypeRock
        "ghost" -> TypeGhost
        "dragon" -> TypeDragon
        "dark" -> TypeDark
        "steel" -> TypeSteel
        "fairy" -> TypeFairy
        else -> Color.Black
    }
}

fun parseStatToColor(stat: Stat): Color {
    return when(stat.stat.name.toLowerCase()) {
        "hp" -> HPColor
        "attack" -> AtkColor
        "defense" -> DefColor
        "special-attack" -> SpAtkColor
        "special-defense" -> SpDefColor
        "speed" -> SpdColor
        else -> Color.White
    }
}

fun parseStatToAbbr(stat: Stat): String {
    return when(stat.stat.name.toLowerCase()) {
        "hp" -> "HP"
        "attack" -> "Atk"
        "defense" -> "Def"
        "special-attack" -> "SpAtk"
        "special-defense" -> "SpDef"
        "speed" -> "Spd"
        else -> ""
    }
}