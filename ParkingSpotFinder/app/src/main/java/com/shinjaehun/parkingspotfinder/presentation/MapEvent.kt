package com.shinjaehun.parkingspotfinder.presentation

import com.google.android.gms.maps.model.LatLng
import com.shinjaehun.parkingspotfinder.domain.ParkingSpot

sealed class MapEvent {
    data object ToggleFalloutMap: MapEvent()
    data class OnMapLongClick(val latLng: LatLng): MapEvent()
    data class OnInfoWindowLongClick(val spot: ParkingSpot): MapEvent()

}
