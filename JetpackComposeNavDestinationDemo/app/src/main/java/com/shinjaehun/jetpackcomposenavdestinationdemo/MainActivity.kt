package com.shinjaehun.jetpackcomposenavdestinationdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.shinjaehun.jetpackcomposenavdestinationdemo.destinations.PostScreenDestination
import com.shinjaehun.jetpackcomposenavdestinationdemo.destinations.ProfileScreenDestination
import com.shinjaehun.jetpackcomposenavdestinationdemo.ui.theme.JetpackComposeNavDestinationDemoTheme
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeNavDestinationDemoTheme {
            // 더 열받는 얘기는 navController로는 parcelizable object를 넘길 수 없데요...
            // 그래서 모든 argument를 풀어서 넘겨야 했다고
//                val navController = rememberNavController()
//                NavHost(
//                    navController = navController,
//                    startDestination = "login"
//                ) {
//                    composable("login") {
//                        LoginScreen(navController)
//                    }
//                    composable(
//                        route = "profile/{name}/{userId}/{timestamp}",
//                        arguments = listOf(
//                            navArgument("name") {
//                                type = NavType.StringType
//                            },
//                            navArgument("userId") {
//                                type = NavType.StringType
//                            },
//                            navArgument("timestamp") {
//                                type = NavType.LongType
//                            },
//                        )
//                    ) {
//                        val name = it.arguments?.getString("name")!!
//                        val userId = it.arguments?.getString("userId")!!
//                        val timestamp = it.arguments?.getLong("timestamp")!!
//
//                        ProfileScreen(
//                            navController = navController,
//                            name = name,
//                            userId = userId,
//                            created = timestamp
//                        )
//                    }
//                    composable("post/{showOnlyPostsByUser}", arguments = listOf(
//                        navArgument("showOnlyPostsByUser") {
//                            type = NavType.BoolType
//                            defaultValue = false
//                        }
//                    )) {
//                        val showOnlyPostsByUser =
//                            it.arguments?.getBoolean("showOnlyPostsByUser") ?: false
//                        PostScreen(showOnlyPostsByUser)
//                    }
//                }

                DestinationsNavHost(navGraph = NavGraphs.root)
            }
        }
    }
}

@Destination(start = true)
@Composable
fun LoginScreen(
//    navController: NavController
    navigator: DestinationsNavigator
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Login Screen")
        Button(onClick = {
//            navController.navigate("profile/philipp/userid/123456789")
            navigator.navigate(
                ProfileScreenDestination(
                    User(
                        name = "Shinjaehun",
                        id = "userid",
                        created = LocalDateTime.now()
                    )
                )
            )
        }) {
            Text("Go to Profile Screen")
        }
    }
}

@Destination
@Composable
fun ProfileScreen(
//    navController: NavController,
//    name: String,
//    userId: String,
//    created: Long
    navigator: DestinationsNavigator,
    user: User
) {
//    val user = remember {
//        User(
//            name = name,
//            id = userId,
//            created = LocalDateTime.ofInstant(
//                Instant.ofEpochMilli(created), ZoneId.systemDefault()
//            )
//        )
//    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Profile Screen: $user", textAlign = TextAlign.Center)
        Button(onClick = {
//            navController.navigate("post/true")
            navigator.navigate(PostScreenDestination())
        }) {
            Text("Go to Post Screen")
        }
    }
}

@Destination
@Composable
fun PostScreen(
    showOnlyPostsByUser: Boolean = false
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Post Screen, $showOnlyPostsByUser")
    }
}