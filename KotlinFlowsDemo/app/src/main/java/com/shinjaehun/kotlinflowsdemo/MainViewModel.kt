package com.shinjaehun.kotlinflowsdemo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.fold
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.reduce
import kotlinx.coroutines.launch

class MainViewModel(
    private val dispatchers: DispatcherProvider
): ViewModel() {

    val countDownFlow = flow<Int> {
        val startingValue = 5
        var currentValue = startingValue
        emit(startingValue)
        while (currentValue > 0) {
            delay(1000L)
            currentValue--
            emit(currentValue)
        }
    }.flowOn(dispatchers.main)

//    private val _sharedFlow = MutableSharedFlow<Int>()
//    val sharedFlow = _sharedFlow.asSharedFlow()

//    init {
////        collectFlow()
//
////        squareNumber(3) // 아무 일도 일어나지 않음
//        viewModelScope.launch {
//            sharedFlow.collect {
//                delay(2000L)
//                println("FIRST FLOW: The received number is $it")
//            }
//        }
//        viewModelScope.launch {
//            sharedFlow.collect {
//                delay(3000L)
//                println("SECOND FLOW: The received number is $it")
//            }
//
//        }
//        squareNumber(3) // 이건 가능함
//    }

    private val _sharedFlow = MutableSharedFlow<Int>(replay = 5)
    val sharedFlow = _sharedFlow.asSharedFlow()

//    init {
//        squareNumber(3) // MutableSharedFlow(replay) replay 값만큼 try 해보고 flow를 collect
//        viewModelScope.launch {
//            sharedFlow.collect {
//                delay(2000L)
//                println("FIRST FLOW: The received number is $it")
//            }
//        }
//        viewModelScope.launch {
//            sharedFlow.collect {
//                delay(3000L)
//                println("SECOND FLOW: The received number is $it")
//            }
//        }
//    }

    init {
        squareNumber(3) // MutableSharedFlow(replay) replay 값만큼 try 해보고 flow를 collect
        viewModelScope.launch(dispatchers.main) {
            sharedFlow.collect {
                delay(2000L)
                println("FIRST FLOW: The received number is $it")
            }
        }
        viewModelScope.launch(dispatchers.main) {
            sharedFlow.collect {
                delay(3000L)
                println("SECOND FLOW: The received number is $it")
            }
        }
    }

//    fun squareNumber(number: Int) {
//        viewModelScope.launch {
//            _sharedFlow.emit(number * number)
//        }
//    }

    fun squareNumber(number: Int) {
        viewModelScope.launch(dispatchers.main) {
            _sharedFlow.emit(number * number)
        }
    }

    private val _stateFlow = MutableStateFlow(0)
    val stateFlow = _stateFlow.asStateFlow()

    fun incrementCounter() {
        _stateFlow.value += 1
    }

//    private fun collectFlow() {
//        viewModelScope.launch {
//            countDownFlow.collect { time ->
//                println("The current time is $time")
//            }
//            countDownFlow.collect { time ->
//                delay(1500L) // 이렇게 하면 1초 + 1.5초 뒤에 결과가 보이겠지?
//                println("The current time is $time")
//            }
//            countDownFlow.collectLatest { time ->
    // 얘는 최신 데이터가 들어왔을 때 이전 데이터를 이용해서 수행하던 suspend fun을 취소하고
    // 새로 들어온 데이터로 suspend fun을 수행
//                delay(1500L)
//                println("The current time is $time")
//            }
//        }
//    }

//    private fun collectFlow() {
//        viewModelScope.launch {
//            countDownFlow
//                .filter { time ->
//                    time % 2 == 0
//                }
//                .map { time ->
//                    time * time
//                }
//                .onEach { time ->
//                    println(time)
//                }
//                .collect { time ->
//                    println("The current time is $time")
//                }
//        }
//    }

//    private fun collectFlow() {
//        countDownFlow.onEach {
//            println(it)
//        }.launchIn(viewModelScope)
//        viewModelScope.launch {
//            countDownFlow.collect {
//
//            }
//        }
//    }

//    private fun collectFlow() {
//        viewModelScope.launch {
//            val count = countDownFlow
//                .filter { time ->
//                    time % 2 == 0
//                }
//                .map { time ->
//                    time * time
//                }
//                .onEach { time ->
//                    println(time)
//                }
//                .count {
//                    it % 2 == 0
//                }
//            println("the count is $count")

//            val count = countDownFlow
//            println("the count is $count")
//        }
//    }

//    private fun collectFlow() {
//        viewModelScope.launch {
//            val reduceResult = countDownFlow
//                .reduce { accumulator, value ->
//                    println("accumulator: $accumulator")
//                    println("value: $value")
//                    accumulator + value
//                }
//            println("The result is $reduceResult")
//        }
//    }

//    private fun collectFlow() {
//        viewModelScope.launch {
//            val reduceResult = countDownFlow
//                .fold(100) { accumulator, value ->
//                    println("accumulator: $accumulator")
//                    println("value: $value")
//                    accumulator + value
//                }
//            println("The result is $reduceResult")
//        }
//    }

    // flatten?
    // [[1, 2], [1, 2, 3]]
    // [1, 2, 1, 2, 3]

//    private fun collectFlow() {
//        val flow1 = flow {
//            emit(1)
//            delay(500L)
//            emit(2)
//        }
//        viewModelScope.launch {
//            flow1.flatMapConcat { value ->
//                flow {
//                    emit(value + 1)
//                    delay(500L)
//                    emit(value + 2)
//                }
//            }.collect { value ->
//                println("the value is $value")
//            }
//
//        }
//    }

//    private fun collectFlow() {
//        val flow1 = (1..5).asFlow()
//        viewModelScope.launch {
//            flow1.flatMapConcat { id -> // .flatMapMerge(), .flatMapLatest()
//                getRecipeById(id)
//            }.collect { value ->
//                println("the value is $value")
//            }
//        }
//    }

//    private fun collectFlow() {
//        val flow = flow {
//            delay(250L)
//            emit("Appetizer")
//            delay(1000L)
//            emit("Main dish")
//            delay(1000L)
//            emit("Dessert")
//        }
//        viewModelScope.launch {
//            flow.onEach {
//                println("FLOW: $it is delivered")
//            }
//                .collect {
//                    println("FLOW: Now eating $it")
//                    delay(1500L)
//                    println("FLOW: Finished eating $it")
//                }
//        }
//    }

//    private fun collectFlow() {
//        val flow = flow {
//            delay(250L)
//            emit("Appetizer")
//            delay(1000L)
//            emit("Main dish")
//            delay(1000L)
//            emit("Dessert")
//        }
//        viewModelScope.launch {
//            flow.onEach {
//                println("FLOW: $it is delivered")
//            }
//                .buffer()
//                .collect {
//                    println("FLOW: Now eating $it")
//                    delay(1500L)
//                    println("FLOW: Finished eating $it")
//                }
//        }
//    }

//    private fun collectFlow() {
//        val flow = flow {
//            delay(250L)
//            emit("Appetizer")
//            delay(1000L)
//            emit("Main dish")
//            delay(1000L)
//            emit("Dessert")
//        }
//        viewModelScope.launch {
//            flow.onEach {
//                println("FLOW: $it is delivered")
//            }
//                .conflate()
//                .collect {
//                    println("FLOW: Now eating $it")
//                    delay(1500L)
//                    println("FLOW: Finished eating $it")
//                }
//        }
//    }

    private fun collectFlow() {
        val flow = flow {
            delay(250L)
            emit("Appetizer")
            delay(1000L)
            emit("Main dish")
            delay(1000L)
            emit("Dessert")
        }
        viewModelScope.launch {
            flow.onEach {
                println("FLOW: $it is delivered")
            }
                .collectLatest {
                    println("FLOW: Now eating $it")
                    delay(1500L)
                    println("FLOW: Finished eating $it")
                }
        }
    }

}