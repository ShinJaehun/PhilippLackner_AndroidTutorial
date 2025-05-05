package com.shinjaehun.jetpackcomposenavigationdrawerdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.rpc.Help
import com.shinjaehun.jetpackcomposenavigationdrawerdemo.ui.theme.JetpackComposeNavigationDrawerDemoTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposeNavigationDrawerDemoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
                    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                    val scope = rememberCoroutineScope()

                    ModalNavigationDrawer(
                        drawerContent = {
//                            ModalDrawerSheet {
//                                Column(
//                                    modifier = Modifier.padding(horizontal = 16.dp)
//                                        .verticalScroll(rememberScrollState())
//                                ) {
//                                    Spacer(Modifier.height(12.dp))
//                                    Text("Drawer Title", modifier = Modifier.padding(16.dp), style = MaterialTheme.typography.titleLarge)
//                                    HorizontalDivider()
//
//                                    Text("Section 1", modifier = Modifier.padding(16.dp), style = MaterialTheme.typography.titleMedium)
//                                    NavigationDrawerItem(
//                                        label = { Text("Item 1") },
//                                        selected = false,
//                                        onClick = { /* Handle click */ }
//                                    )
//                                    NavigationDrawerItem(
//                                        label = { Text("Item 2") },
//                                        selected = false,
//                                        onClick = { /* Handle click */ }
//                                    )
//
//                                    HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
//
//                                    Text("Section 2", modifier = Modifier.padding(16.dp), style = MaterialTheme.typography.titleMedium)
//                                    NavigationDrawerItem(
//                                        label = { Text("Settings") },
//                                        selected = false,
//                                        icon = { Icon(Icons.Outlined.Settings, contentDescription = null) },
//                                        badge = { Text("20") }, // Placeholder
//                                        onClick = { /* Handle click */ }
//                                    )
////                                    NavigationDrawerItem(
////                                        label = { Text("Help and feedback") },
////                                        selected = false,
////                                        icon = { Icon(Icons.AutoMirrored.Outlined.Help, contentDescription = null) },
////                                        onClick = { /* Handle click */ },
////                                    )
//                                    Spacer(Modifier.height(12.dp))
//                                }
//                            }

                            ModalDrawerSheet {
                                Column(
                                    modifier = Modifier.padding(horizontal = 16.dp)
                                ) {

                                    DrawerHeader()
                                    DrawerBody(
                                        items = listOf(
                                            MenuItem(
                                                id = "home",
                                                title = "Home",
                                                contentDescription = "Go to home screen",
                                                icon = Icons.Default.Home
                                            ),
                                            MenuItem(
                                                id = "settings",
                                                title = "Settings",
                                                contentDescription = "Go to settings screen",
                                                icon = Icons.Default.Settings
                                            ),
                                            MenuItem(
                                                id = "help",
                                                title = "Help",
                                                contentDescription = "Go to Help screen",
                                                icon = Icons.Default.Info
                                            )
                                        ),
                                        onItemClick = {
                                            println("clicked on ${it.title}")
                                        }
                                    )
                                }
                            }

                        },
                        drawerState = drawerState,
                        gesturesEnabled = drawerState.isOpen

                    ) {
                        Scaffold(
                            modifier = Modifier
                                .fillMaxSize()
                                .nestedScroll(scrollBehavior.nestedScrollConnection),
                            topBar = {
                                TopAppBar(
                                    title = {
                                        Text(text = "My notes")
                                    },
                                    navigationIcon = {
                                        IconButton(
                                            onClick = {
                                                scope.launch {
                                                    if (drawerState.isClosed) {
                                                        drawerState.open()
                                                    } else {
                                                        drawerState.close()
                                                    }
                                                }
                                            }
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.Menu,
                                                contentDescription = "Go Back"
                                            )
                                        }
                                    },
                                    actions = {
                                        IconButton(
                                            onClick = {

                                            }
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.FavoriteBorder,
                                                contentDescription = "Mak as Favorite"
                                            )
                                        }
                                        IconButton(
                                            onClick = {

                                            }
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.Edit,
                                                contentDescription = "Mak as Edit note"
                                            )
                                        }
                                    },
                                    scrollBehavior = scrollBehavior,
                                )
                            },
                        ) { values ->
                            LazyColumn(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(values)
                            ) {
                                items(100) {
                                    Text(
                                        text = "Items $it",
                                        modifier = Modifier.padding(16.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
