package com.shinjaehun.kotlinflowsdemo

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

fun flowsDemo() {
    val flow = flow<Int> {
        // cold flow
        repeat(10) {
            emit(it)
            println("Emitted $it")
            delay(1000L)
        }
    }

//    flow.launchIn(GlobalScope)
    GlobalScope.launch {
        flow.collect {
            println(it.toString())
        }
    }
}