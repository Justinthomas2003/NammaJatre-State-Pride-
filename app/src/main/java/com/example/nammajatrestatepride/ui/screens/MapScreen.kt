package com.example.nammajatrestatepride.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*

@Composable
fun MapScreen() {
    val singapore = LatLng(1.35, 103.87) // Placeholder for fair location
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(singapore, 10f)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Text("📍 Fair Location & Facilities", style = MaterialTheme.typography.headlineMedium, modifier = Modifier.padding(16.dp))
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState
        ) {
            Marker(state = MarkerState(position = singapore), title = "Fair Grounds")
            // Add markers for parking, food, etc.
        }
    }
}