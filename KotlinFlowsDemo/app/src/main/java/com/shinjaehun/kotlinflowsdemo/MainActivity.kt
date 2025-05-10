package com.shinjaehun.kotlinflowsdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.shinjaehun.kotlinflowsdemo.ui.theme.KotlinFlowsDemoTheme
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

//    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // xml에서 flow를 사용하는 방법
//        lifecycleScope.launch { // 그냥 이렇게만 하면 안전하지 않은가?
//            viewModel.stateFlow.collectLatest { number ->
//                binding.tvCounter.text = number.toString()
//            }
//        }

//        lifecycleScope.launch {
//            repeatOnLifecycle(Lifecycle.State.STARTED) {
//                viewModel.stateFlow.collectLatest { number ->
//                    binding.tvCounter.text = number.toString()
//                }
//            }
//        }

//        collectLatestLifecycleFlow(viewModel.stateFlow) { number ->
//            binding.tvCounter.text = number.toString()
//        }

//        enableEdgeToEdge()

//        flowsDemo()

        setContent {
            KotlinFlowsDemoTheme {
                // kotlin flow
//                val viewModel = viewModel<MainViewModel>()
////                val time = viewModel.countDownFlow.collectAsState(initial = 10)
//                val count = viewModel.stateFlow.collectAsState(initial = 0)
//                // compose에서 쓰지 말라고 하는데요...?
//                // 앱이 백그라운드에서 실행되더라도 flow가 collect할 가능성이 있기 때문?
//
////                LaunchedEffect(key1 = true) {
//                // compose에서는 이런 식으로 side effect를 사용하는 것이 정석!
////                    viewModel.sharedFlow.collect { number ->
////
////                    }
////                }
//
////                val lifecycleOwner = LocalLifecycleOwner.current
////                val countLifecycleAware = remember(viewModel.stateFlow, lifecycleOwner) {
////                    viewModel.stateFlow.flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
////                }
////                val count by countLifecycleAware.collectAsState(initial = 0)
//                // 이렇게 하는게 맞는지 모르겠는데 수명주기를 인식하도록 해봤음... 어쨌든 동작은 함...
//                // https://velog.io/@heetaeheo/Jetpack-Compose%EC%97%90%EC%84%9C-%EC%88%98%EB%AA%85-%EC%A3%BC%EA%B8%B0%EC%97%90-%EB%A7%9E%EA%B2%8C-collect-flows-%EC%82%AC%EC%9A%A9%ED%95%98%EB%8A%94-%EB%B0%A9%EB%B2%95
//
////                viewModel.stateFlow.value = 3 // 이렇게 할 수 없지!
//
//                Box(modifier = Modifier.fillMaxSize()) {
////                    Text(
////                        text = time.value.toString(),
////                        fontSize = 30.sp,
////                        modifier = Modifier.align(Alignment.Center)
////                    )
//                    Button(onClick = { viewModel.incrementCounter()}) {
//                        Text(text="Counter: ${count.value}")
////                        Text(text="Counter: ${count}")
//                    }
//                }

                // flow combine, zip, merge
//                val viewModel = viewModel<CombineViewModel>()
//                Text(text = viewModel.numberString)

                // hot flow vs cold flow
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    LocationFlowDemo()
//                }

                // state vs stateFlow
                val viewModel = viewModel<StateVSStateFlowViewModel>()
//                val composeColor = viewModel.composeColor
//                val flowColor by viewModel.color.collectAsStateWithLifecycle()
                val flowColor by viewModel.color.collectAsState()
                Box(
                    modifier = Modifier
                        .fillMaxSize()
//                        .background(Color(composeColor))
                        .background(Color(flowColor))
                        .clickable { viewModel.generateNewColor() }
                )
            }
        }
    }
}

//fun <T> ComponentActivity.collectLatestLifecycleFlow(flow: Flow<T>, collect: suspend (T) -> Unit) {
//    lifecycleScope.launch {
//        repeatOnLifecycle(Lifecycle.State.STARTED) {
//            flow.collectLatest(collect)
//        }
//    }
//}