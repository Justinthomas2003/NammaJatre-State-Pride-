package com.example.nammajatrestatepride.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.nammajatrestatepride.model.UiState
import com.example.nammajatrestatepride.ui.components.ErrorScreen
import com.example.nammajatrestatepride.ui.components.LoadingIndicator
import com.example.nammajatrestatepride.ui.components.LostFoundCard
import com.example.nammajatrestatepride.viewmodel.LostFoundViewModel

@Composable
fun LostFoundScreen(
    viewModel: LostFoundViewModel = hiltViewModel()
) {
    val postsState by viewModel.postsState.collectAsState()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* TODO: Navigate to add post screen */ }
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Add Lost & Found Post")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Text(
                text = "🔍 Lost & Found",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            when (val state = postsState) {
                is UiState.Loading -> LoadingIndicator()
                is UiState.Error -> ErrorScreen(state.message) { viewModel.fetchPosts() }
                is UiState.Success -> {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(state.data) { post ->
                            LostFoundCard(
                                post = post,
                                onMarkResolved = { viewModel.markAsResolved(post.id) }
                            )
                        }
                    }
                }
                is UiState.Idle -> {} // Handle Idle state
            }
        }
    }
}