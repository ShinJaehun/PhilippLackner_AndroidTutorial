package com.shinjaehun.instagramui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.shinjaehun.instagramui.ui.theme.InstagramUITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            InstagramUITheme {
                Box(modifier = Modifier.safeDrawingPadding()) {
                    ProfileScreen()
                }
//                ProfileScreen(modifier = Modifier.safeDrawingPadding())
//                Scaffold(
//
//                    modifier = Modifier.fillMaxSize()
//                ) { innerPadding ->
//                    ProfileScreen(
//                        modifier = Modifier.padding(innerPadding)
//                    )
//                }

            }
        }
    }
}
