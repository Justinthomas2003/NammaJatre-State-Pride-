package com.example.nammajatrestatepride.tickets

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.nammajatrestatepride.model.UiState
import com.example.nammajatrestatepride.navigation.Screen

@Composable
fun ParticipationBookingScreen(navController: NavController, viewModel: TicketViewModel = hiltViewModel()) {
    var eventId by remember { mutableStateOf("") }
    var details by remember { mutableStateOf("") }
    val bookingState by viewModel.bookingState.collectAsState()

    LaunchedEffect(bookingState) {
        if (bookingState is UiState.Success) {
            navController.navigate("${Screen.TicketConfirmation.route}/${(bookingState as UiState.Success).data}")
        }
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text("🎭 Event Participation", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(24.dp))
        OutlinedTextField(value = eventId, onValueChange = { eventId = it }, label = { Text("Event ID") })
        OutlinedTextField(value = details, onValueChange = { details = it }, label = { Text("Details") })
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { viewModel.bookParticipation(eventId) }) { Text("Book Participation") } // Removed details parameter
        if (bookingState is UiState.Loading) CircularProgressIndicator()
        if (bookingState is UiState.Error) Text((bookingState as UiState.Error).message, color = MaterialTheme.colorScheme.error)
    }
}