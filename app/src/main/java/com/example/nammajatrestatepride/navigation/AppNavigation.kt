@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.nammajatrestatepride.navigation

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.*
import com.example.nammajatrestatepride.auth.AuthViewModel
import com.example.nammajatrestatepride.ui.screens.*
import com.example.nammajatrestatepride.auth.*
import com.example.nammajatrestatepride.profile.ProfileScreen
import com.example.nammajatrestatepride.tickets.*
import com.example.nammajatrestatepride.gallery.GalleryScreen
import com.example.nammajatrestatepride.guide.GuideScreen
import com.example.nammajatrestatepride.support.SupportScreen

@Composable
fun AppNavigation(authViewModel: AuthViewModel = hiltViewModel()) {
    val navController = rememberNavController()
    val authState by authViewModel.authState.collectAsState()

    val startDestination = Screen.Home.route

    Scaffold { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = Modifier
        ) {
            composable(Screen.SignIn.route) { SignInScreen(navController) }
            composable(Screen.Register.route) { RegisterScreen(navController) }

            composable(Screen.Home.route) { HomeScreen(navController) }
            composable(Screen.Schedule.route) { ScheduleScreen() }
            composable(Screen.Map.route) { MapScreen() }
            composable(Screen.Stories.route) { StoriesScreen() }
            composable(Screen.LostFound.route) { LostFoundScreen() }
            composable(Screen.Profile.route) { ProfileScreen(navController) }
            composable(Screen.Gallery.route) { GalleryScreen() }
            composable(Screen.Guide.route) { GuideScreen() }
            composable(Screen.Support.route) { SupportScreen() }

            composable(Screen.FairEntryBooking.route) { FairEntryBookingScreen(navController) }
            composable(Screen.ParticipationBooking.route) { ParticipationBookingScreen(navController) }
            composable("${Screen.TicketConfirmation.route}/{ticketId}") { backStackEntry ->
                TicketConfirmationScreen(backStackEntry.arguments?.getString("ticketId") ?: "")
            }
        }
    }
}