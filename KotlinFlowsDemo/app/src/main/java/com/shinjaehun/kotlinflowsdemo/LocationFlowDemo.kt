package com.shinjaehun.kotlinflowsdemo

import android.annotation.SuppressLint
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import android.Manifest
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.shareIn

@Composable
fun LocationFlowDemo(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    LaunchedEffect(key1 = true) {
        ActivityCompat.requestPermissions(
            context as ComponentActivity,
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            ),
            0
        )
    }

    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        val scope = rememberCoroutineScope()
        val flow = remember {
            observeLocation(context)
                .shareIn( // hot flow로 바꿔줍니다!
                    scope,
                    SharingStarted.Eagerly
                )
        }

        val location1 by flow.collectAsState(initial = null)
        val location2 by flow.collectAsState(initial = null)

        LaunchedEffect(location1) {
            println("Location1 update: (${location1?.latitude}, ${location1?.longitude})")
        }
        LaunchedEffect(location2) {
            println("Location1 update: (${location2?.latitude}, ${location2?.longitude})")
        }
    }
}