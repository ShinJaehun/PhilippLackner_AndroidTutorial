package com.shinjaehun.ktorclientandroid.presentation.posts

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.shinjaehun.ktorclientandroid.ui.theme.KtorClientAndroidTheme

private const val TAG = "PostsScreen"

@Composable
fun PostsScreen(
    viewModel: PostsViewModel = hiltViewModel()
) {

    // 아... 이렇게 까지 할 일이 아니었는데
    // 내가 너무너무너무 꼬아서 생각해버렸다...ㅠㅠ
//    LaunchedEffect(key1 = true) {
//        viewModel.getPosts()
//    }

//    LaunchedEffect(Unit) {
//        viewModel.getPosts()
//    }

//    val lifecycleOwner = LocalLifecycleOwner.current
//    DisposableEffect(key1 = lifecycleOwner) {
//        val observer = LifecycleEventObserver { _, event ->
//            if(event == Lifecycle.Event.ON_START) {
//                viewModel.getPosts()
//            }
//        }
//        lifecycleOwner.lifecycle.addObserver(observer)
//        onDispose {
//            lifecycleOwner.lifecycle.removeObserver(observer)
//        }
//    }

    val posts = viewModel.state.value.postResponse
    val isLoading = viewModel.state.value.isLoading
//            viewModel.getPosts() // 이렇게 하면 큰일나요!! 계속 getPosts() 실행함...

    Log.i(TAG, "posts: $posts")

    KtorClientAndroidTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            floatingActionButton = {
                FloatingActionButton(
                    contentColor = Color.White,
                    containerColor = Color.Cyan,
                    shape = CircleShape,
                    onClick = {
                    }
                ) {
                    Icon(Icons.Filled.Add, "post")
                }
            }
        ) { innerPadding ->
            LazyColumn(modifier = Modifier.padding(innerPadding)) {

                if (posts != null) {
                    items(posts) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            Text(text = it.title, fontSize = 20.sp)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(text = it.body, fontSize = 14.sp)
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            if(isLoading){
                CircularProgressIndicator()
            }
        }
    }
}