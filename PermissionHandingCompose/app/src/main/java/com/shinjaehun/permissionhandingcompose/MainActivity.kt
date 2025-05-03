package com.shinjaehun.permissionhandingcompose

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.accompanist.permissions.shouldShowRationale
import com.shinjaehun.permissionhandingcompose.ui.theme.PermissionHandingComposeTheme

@ExperimentalPermissionsApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PermissionHandingComposeTheme {
                val permissionsState = rememberMultiplePermissionsState(
                    permissions = listOf(
                        Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.CAMERA
                    )
                )

                val lifecycleOwner = LocalLifecycleOwner.current
                DisposableEffect(
                    key1 = lifecycleOwner,
                    effect = {
                        val observer = LifecycleEventObserver { _, event ->
                            if (event == Lifecycle.Event.ON_START) {
                                // 영상에서는 ON_RESUME으로 했는데...
                                // It's actually ON_START. onResume is triggered
                                // when the permission dialog is closed
                                // which is not what should happen.
                                permissionsState.launchMultiplePermissionRequest()
                            }
                        }
                        lifecycleOwner.lifecycle.addObserver(observer)

                        onDispose {
                            lifecycleOwner.lifecycle.removeObserver(observer)
                        }
                    }
                )

                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    permissionsState.permissions.forEach {perm ->
                        when (perm.permission) {
                            Manifest.permission.CAMERA -> {
                                when {
                                    perm.status.isGranted -> {

                                        Text(text = "Camera permission accepted")
                                    }
                                    perm.status.shouldShowRationale -> {
                                        Text(text = "Camera permission is needed to access the camera")
                                    }
                                    perm.status.isPermanentlyDenied() -> {
                                        // !perm.status.isGranted && !.perm.status.shouldShowRationale
                                        Text(text = "Camera permission was permanently denied.\nYou can enable it in the app.")
                                    }
                                }
                            }
                            Manifest.permission.RECORD_AUDIO -> {
                                when {
                                    perm.status.isGranted -> {
                                        Text(text = "Record audio permission accepted")
                                    }
                                    perm.status.shouldShowRationale -> {
                                        Text(text = "Record audio permission is needed to access the recorder")
                                    }
                                    perm.status.isPermanentlyDenied() -> {
                                        Text(text = "Record audio permission was permanently denied.\nYou can enable it in the app.")
                                    }
                                }
                            }
                        }
                    }
//                    Text(text = "Camera permission accepted")
//                    Text(text = "Record audio permission accepted")
                }
            }
        }
    }
}
