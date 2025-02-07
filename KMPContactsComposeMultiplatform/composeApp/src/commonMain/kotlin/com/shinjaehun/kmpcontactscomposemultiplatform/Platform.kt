package com.shinjaehun.kmpcontactscomposemultiplatform

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform