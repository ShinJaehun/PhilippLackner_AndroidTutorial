package com.shinjaehun.ktorclientandroid

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import com.shinjaehun.ktorclientandroid.data.remote.PostsService
import com.shinjaehun.ktorclientandroid.data.remote.dto.PostRequest
import com.shinjaehun.ktorclientandroid.data.remote.dto.PostResponse
import com.shinjaehun.ktorclientandroid.ui.theme.KtorClientAndroidTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

private const val TAG = "MainActivity"

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

//    private val service = PostsService.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: MainViewModel = hiltViewModel()
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
                                Log.i(TAG, "FAB")
                                // todo
//                                lifecycleScope.launch {
//                                    val response = service.createPost(PostRequest(body = "안녕하세요", title = "인사", user_id = 1))
//                                    Log.i(TAG, "response: ${response}")
//                                }
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
    }
}
