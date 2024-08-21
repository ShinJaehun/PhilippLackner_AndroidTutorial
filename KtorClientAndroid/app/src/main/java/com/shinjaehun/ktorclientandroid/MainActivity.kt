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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.shinjaehun.ktorclientandroid.data.remote.PostsService
import com.shinjaehun.ktorclientandroid.data.remote.dto.PostRequest
import com.shinjaehun.ktorclientandroid.data.remote.dto.PostResponse
import com.shinjaehun.ktorclientandroid.presentation.auth.AuthScreen
import com.shinjaehun.ktorclientandroid.presentation.posts.PostsScreen
import com.shinjaehun.ktorclientandroid.presentation.posts.PostsState
import com.shinjaehun.ktorclientandroid.ui.theme.KtorClientAndroidTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

private const val TAG = "MainActivity"

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    // This New Devise API Gem Makes User Auth So Simple! | Ruby On Rails 7 Tutorial
    // https://youtu.be/sLcLwVCBU0c?feature=shared

    // android_crud_api_test_devise_api 프로젝트 참고
    // https://github.com/Deanout/devise_api/blob/main/frontend/main.js

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            setContent {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "auth_screen") {
                    composable("auth_screen") {
                        AuthScreen(onNavigate = navController::navigate)
                    }
                    composable("posts_screen") {
                        PostsScreen()
                    }
                }
            }

        }
    }
}
