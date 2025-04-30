package com.shinjaehun.jetpackcomposebottomnavigationbagesexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.shinjaehun.jetpackcomposebottomnavigationbagesexample.ui.theme.JetpackComposeBottomNavigationBagesExampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposeBottomNavigationBagesExampleTheme {
                val items = listOf(
                    BottomNavItem(
                        name = "Home",
                        route = "home",
                        icon = Icons.Filled.Home,
                    ),
                    BottomNavItem(
                        name = "Chat",
                        route = "chat",
                        icon = Icons.Filled.Notifications,
                        badgeCount = 45
                    ),
                    BottomNavItem(
                        name = "Settings",
                        route = "settings",
                        icon = Icons.Filled.Settings,
                    )
                )
//                var selectedItemIndex by rememberSaveable {
//                    mutableStateOf(0)
//                }

                val navController = rememberNavController()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        bottomBar = {

//                            NavigationBar {
//                                items.forEachIndexed { index, item ->
//                                    NavigationBarItem(
//                                        selected = selectedItemIndex == index,
//                                        onClick = {
//                                            selectedItemIndex = index
////                                            navController.navigate(item.route)
//                                        },
//                                        label = {
//                                            Text(text = item.name)
//                                        },
//                                        icon = {
//                                            BadgedBox(
//                                                badge = {
//                                                    if (item.badgeCount > 0) {
//                                                        Badge {
//                                                            Text(text = item.badgeCount.toString())
//                                                        }
//                                                    }
//                                                }
//                                            ) {
//                                                Icon(
//                                                    imageVector = item.icon,
//                                                    contentDescription = item.name
//                                                )
//                                            }
//                                        }
//                                    )
//                                }
//                            }

                            BottomNavigationBar(
                                items = items,
                                navController = navController,
                                onItemClick = {
                                    navController.navigate(it.route) // 이게 hoisting인가?
                                }
                            )

                        }
                    ) {
                        Box(modifier = Modifier.padding(it)) {
                            Navigation(navController = navController)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen()
        }
        composable("chat") {
            ChatScreen()
        }
        composable("settings") {
            SettingsScreen()
        }
    }
}

// http://github.com/philipplackner/M3-BottomNavigation/blob/master/app/src/main/java/com/plcoding/m3_bottomnavigation/MainActivity.kt
// 비슷하게 구현

//@Composable
//fun BottomNavigationBar(
//    items: List<BottomNavItem>,
//    navController: NavController,
//) {
//    var selectedItemIndex by rememberSaveable {
//        mutableStateOf(0)
//    }
//
//    NavigationBar {
//        items.forEachIndexed { index, item ->
//            NavigationBarItem(
//                selected = selectedItemIndex == index,
//                onClick = {
//                    selectedItemIndex = index
//                    navController.navigate(item.route)
//                },
//                label = {
//                    Text(text = item.name)
//                },
//                icon = {
//                    BadgedBox(
//                        badge = {
//                            if (item.badgeCount > 0) {
//                                Badge {
//                                    Text(text = item.badgeCount.toString())
//                                }
//                            }
//                        }
//                    ) {
//                        Icon(
//                            imageVector = item.icon,
//                            contentDescription = item.name
//                        )
//                    }
//                }
//            )
//        }
//    }
//}

// https://github.com/philipplackner/BottomNavWithBadges/blob/master/app/src/main/java/com/plcoding/bottomnavwithbadges/MainActivity.kt
// 비슷하게 구현
@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem>,
    navController: NavController,
//    modifier: Modifier = Modifier,
    onItemClick: (BottomNavItem) -> Unit
) {
//    var selectedItemIndex by rememberSaveable {
//        mutableStateOf(0)
//    }
    val backStackEntry = navController.currentBackStackEntryAsState()

    NavigationBar {
        items.forEach { item ->
            NavigationBarItem(
                selected = item.route == backStackEntry.value?.destination?.route,
                onClick = {
                    onItemClick(item)
                },
                label = {
                    Text(text = item.name)
                },
                icon = {
                    BadgedBox(
                        badge = {
                            if (item.badgeCount > 0) {
                                Badge {
                                    Text(text = item.badgeCount.toString())
                                }
                            }
                        }
                    ) {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.name
                        )
                    }
                }
            )
        }
    }
}

@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Home Screen")
    }
}

@Composable
fun ChatScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Chat Screen")
    }
}

@Composable
fun SettingsScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Settings Screen")
    }
}