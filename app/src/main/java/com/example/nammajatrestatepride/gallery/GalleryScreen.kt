package com.example.nammajatrestatepride.gallery

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.nammajatrestatepride.model.UiState

@Composable
fun GalleryScreen(viewModel: GalleryViewModel = hiltViewModel()) {
    val photosState by viewModel.photosState.collectAsState()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("📸 Festival Gallery", style = MaterialTheme.typography.headlineMedium)
        when (photosState) {
            is UiState.Success -> {
                LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = Modifier.fillMaxSize()) {
                    items((photosState as UiState.Success).data) { photo ->
                        AsyncImage(
                            model = photo.url,
                            contentDescription = photo.description,
                            modifier = Modifier.size(150.dp).padding(8.dp)
                        )
                    }
                }
            }
            is UiState.Loading -> CircularProgressIndicator()
            is UiState.Error -> Text((photosState as UiState.Error).message)
            is UiState.Idle -> Unit  // Handle Idle state
        }
    }
}