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
fun FairEntryBookingScreen(navController: NavController, viewModel: TicketViewModel = hiltViewModel()) {
    var count by remember { mutableStateOf(1) }
    var date by remember { mutableStateOf("") }
    val bookingState by viewModel.bookingState.collectAsState()

    LaunchedEffect(bookingState) {
        if (bookingState is UiState.Success) {
            navController.navigate("${Screen.TicketConfirmation.route}/${(bookingState as UiState.Success).data}")
        }
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text("🎫 Fair Entry Ticket", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(24.dp))
        OutlinedTextField(value = count.toString(), onValueChange = { count = it.toIntOrNull() ?: 1 }, label = { Text("Visitor Count") })
        OutlinedTextField(value = date, onValueChange = { date = it }, label = { Text("Date") })
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { viewModel.bookFairEntry(count, date) }) { Text("Book Ticket") }
        if (bookingState is UiState.Loading) CircularProgressIndicator()
        if (bookingState is UiState.Error) Text((bookingState as UiState.Error).message, color = MaterialTheme.colorScheme.error)
    }
}