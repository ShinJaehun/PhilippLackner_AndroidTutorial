package com.shinjaehun.parkingspotfinder.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ToggleOff
import androidx.compose.material.icons.filled.ToggleOn
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.gms.maps.UiSettings
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import kotlinx.coroutines.launch

@Composable
fun MapScreen(
    viewModel: MapsViewModel = viewModel(),
    modifier: Modifier = Modifier
) {

    val snackbarHostState = remember { SnackbarHostState() }
//    val scope = rememberCoroutineScope()

    val uiSettings = remember {
        MapUiSettings(zoomControlsEnabled = false)
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        floatingActionButton = {
//            var clickCount by remember { mutableStateOf(0) }
            FloatingActionButton(
                onClick = {
                    // show snackbar as a suspend function
//                    scope.launch {
//                        snackbarHostState.showSnackbar(
//                            "Snackbar # ${++clickCount}"
//                        )
//                    }
                    viewModel.onEvent(MapEvent.ToggleFalloutMap)
                }
            ) {
                Icon(
                    imageVector = if (viewModel.state.isFalloutMap){
                            Icons.Filled.ToggleOff
                        } else Icons.Default.ToggleOn,
                    contentDescription = "Toggle Fallout map"
                )
//                Text("Show snackbar")
            }
        },
//        content = { innerPadding ->
//            Text(
//                text = "Body content",
//                modifier = Modifier.padding(innerPadding).fillMaxSize().wrapContentSize()
//            )
//        }
    ) {
        GoogleMap(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            properties = viewModel.state.properties,
            uiSettings = uiSettings,
            onMapLongClick = {
                viewModel.onEvent(MapEvent.OnMapLongClick(it))
            },
        ) {
            viewModel.state.parkingSpots.forEach { spot ->
                Marker(
                    state = MarkerState(position = LatLng(spot.lat, spot.lng)),
                    title = "Parking spot (${spot.lat}, ${spot.lng})",
                    snippet = "Long click to delete",
                    onInfoWindowLongClick = {
                        viewModel.onEvent(
                            MapEvent.OnInfoWindowLongClick(spot)
                        )
                    },
                    onClick = {
                        it.showInfoWindow()
                        true
                    },
                    icon = BitmapDescriptorFactory.defaultMarker(
                        BitmapDescriptorFactory.HUE_GREEN
                    )
                )
            }
        }
    }
}