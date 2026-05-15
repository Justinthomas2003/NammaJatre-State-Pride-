package com.example.nammajatrestatepride.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.nammajatrestatepride.model.Event
import com.example.nammajatrestatepride.model.UiState
import com.example.nammajatrestatepride.ui.components.ErrorScreen
import com.example.nammajatrestatepride.ui.components.EventCard
import com.example.nammajatrestatepride.ui.components.LoadingIndicator
import com.example.nammajatrestatepride.viewmodel.EventViewModel

@Composable
fun ScheduleScreen(
    viewModel: EventViewModel = hiltViewModel()
) {
    val eventState by viewModel.eventState.collectAsState()
    val currentEvent by viewModel.currentEvent.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "🎪 Live Event Schedule",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        when (val state = eventState) {
            is UiState.Loading -> LoadingIndicator()
            is UiState.Error -> ErrorScreen(state.message) { viewModel.fetchEvents() }
            is UiState.Success -> {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    if (currentEvent != null) {
                        item {
                            Text(
                                text = "Currently Happening 🔥",
                                style = MaterialTheme.typography.labelLarge,
                                modifier = Modifier.padding(8.dp)
                            )
                            EventCard(currentEvent!!, isOngoing = true)
                            Spacer(modifier = Modifier.height(16.dp))
                        }
                    }

                    item {
                        Text(
                            text = "Upcoming Events",
                            style = MaterialTheme.typography.labelLarge,
                            modifier = Modifier.padding(8.dp)
                        )
                    }

                    items(state.data) { event ->
                        EventCard(event)
                    }
                }
            }
            is UiState.Idle -> {} // Handle Idle state
        }
    }
}