package com.example.nammajatrestatepride.profile

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.nammajatrestatepride.model.UiState
import com.example.nammajatrestatepride.model.Ticket
import com.example.nammajatrestatepride.ui.components.TicketCard

@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: ProfileViewModel = hiltViewModel()
) {

    val userState by viewModel.userState.collectAsState()
    val ticketsState by viewModel.ticketsState.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        item {
            Text(
                text = "👤 Profile",
                style = MaterialTheme.typography.headlineMedium
            )
        }

        when (userState) {

            is UiState.Success -> {

                val user = (userState as UiState.Success).data

                item {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {

                        Text(
                            text = "Name: ${user.name}",
                            style = MaterialTheme.typography.bodyLarge
                        )

                        Text(
                            text = "Email: ${user.email}",
                            style = MaterialTheme.typography.bodyLarge
                        )

                        Button(
                            onClick = {
                                viewModel.logout()
                                navController.navigate("signin") {
                                    popUpTo(0)
                                }
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Logout")
                        }
                    }
                }
            }

            is UiState.Loading -> {
                item {
                    CircularProgressIndicator()
                }
            }

            is UiState.Error -> {
                item {
                    Text(
                        text = (userState as UiState.Error).message,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }

            is UiState.Idle -> {
                item { }
            }
        }

        item {
            Spacer(modifier = Modifier.height(12.dp))
        }

        item {
            Text(
                text = "🎫 Your Tickets",
                style = MaterialTheme.typography.headlineSmall
            )
        }

        when (ticketsState) {

            is UiState.Success -> {

                val tickets =
                    (ticketsState as UiState.Success<List<Ticket>>).data

                if (tickets.isEmpty()) {

                    item {
                        Text("No tickets booked yet.")
                    }

                } else {

                    items(tickets) { ticket ->

                        TicketCard(ticket = ticket)

                    }
                }
            }

            is UiState.Loading -> {

                item {
                    CircularProgressIndicator()
                }
            }

            is UiState.Error -> {

                item {
                    Text(
                        text = (ticketsState as UiState.Error).message,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }

            is UiState.Idle -> {

                item { }
            }
        }
    }
}