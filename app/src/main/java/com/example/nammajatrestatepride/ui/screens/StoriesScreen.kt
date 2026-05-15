package com.example.nammajatrestatepride.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.nammajatrestatepride.model.UiState
import com.example.nammajatrestatepride.ui.components.ErrorScreen
import com.example.nammajatrestatepride.ui.components.LoadingIndicator
import com.example.nammajatrestatepride.viewmodel.StoriesViewModel

@Composable
fun StoriesScreen(
    viewModel: StoriesViewModel = hiltViewModel()
) {
    val storiesState by viewModel.storiesState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "📖 Cultural Stories",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        when (val state = storiesState) {
            is UiState.Loading -> LoadingIndicator()
            is UiState.Error -> ErrorScreen(state.message) { viewModel.fetchStories() }
            is UiState.Success -> {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(state.data) { story ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            shape = MaterialTheme.shapes.large
                        ) {
                            Column(modifier = Modifier.fillMaxWidth()) {
                                if (story.imageUrl.isNotEmpty()) {
                                    AsyncImage(
                                        model = story.imageUrl,
                                        contentDescription = story.title,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(200.dp),
                                        contentScale = ContentScale.Crop
                                    )
                                }
                                Column(modifier = Modifier.padding(16.dp)) {
                                    Text(
                                        text = story.title,
                                        style = MaterialTheme.typography.headlineSmall
                                    )
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text(
                                        text = story.content,
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                }
                            }
                        }
                    }
                }
            }
            is UiState.Idle -> {} // Handle Idle state
        }
    }
}