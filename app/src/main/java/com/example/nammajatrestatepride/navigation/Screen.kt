package com.example.nammajatrestatepride.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Help
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(
    val route: String,
    val title: String,
    val icon: ImageVector? = null
) {
    object Home : Screen("home", "Home", Icons.Filled.Home)
    object Schedule : Screen("schedule", "Schedule", Icons.Filled.Schedule)
    object Map : Screen("map", "Map", Icons.Filled.LocationOn)
    object Stories : Screen("stories", "Stories", Icons.Filled.Book)
    object LostFound : Screen("lostfound", "Lost & Found", Icons.Filled.Search)
    object Profile : Screen("profile", "Profile", Icons.Filled.Person)
    object Gallery : Screen("gallery", "Gallery", Icons.Filled.Photo)
    object Guide : Screen("guide", "Guide", Icons.Filled.Info)
    object Support : Screen("support", "Support", Icons.AutoMirrored.Filled.Help) // Fixed deprecated icon

    // Auth screens (not in bottom nav)
    object SignIn : Screen("signin", "Sign In")
    object Register : Screen("register", "Register")

    // Ticket screens
    object FairEntryBooking : Screen("fair_entry_booking", "Fair Entry")
    object ParticipationBooking : Screen("participation_booking", "Participation")
    object TicketConfirmation : Screen("ticket_confirmation", "Confirmation")
}