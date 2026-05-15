// app/src/main/java/com/example/nammajatrestatepride/ui/screens/HomeScreen.kt
package com.example.nammajatrestatepride.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.nammajatrestatepride.auth.AuthViewModel
import com.example.nammajatrestatepride.navigation.Screen
import com.example.nammajatrestatepride.ui.components.*
import com.example.nammajatrestatepride.ui.theme.*
import androidx.hilt.navigation.compose.hiltViewModel
import java.time.LocalTime
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.font.FontWeight

@Composable
fun HomeScreen(navController: NavController) {
    val authViewModel: AuthViewModel = hiltViewModel()
    val user = authViewModel.checkCurrentUser()
    val userName = user?.displayName ?: "Guest"

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                FestiveOrange.copy(alpha = 0.20f),
                                FestiveYellow.copy(alpha = 0.08f),
                                BackgroundLight
                            )
                        )
                    )
            )

            Column(modifier = Modifier.fillMaxSize()) {
                TopAppBarHome(
                    user = user,
                    onSignInClick = { navController.navigate(Screen.SignIn.route) },
                    onRegisterClick = { navController.navigate(Screen.Register.route) },
                    onProfileClick = { navController.navigate(Screen.Profile.route) },
                    onOpenSettings = { /* navController.navigate("settings") */ }
                )

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    contentPadding = PaddingValues(bottom = 16.dp)
                ) {
                    item {
                        StoriesCarousel(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp),
                            onStoryClick = { eventId ->
                                navController.navigate("event/$eventId")
                            }
                        )
                    }

                    item {
                        WelcomeSection(userDisplayName = user?.displayName)
                    }

                    item {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = "Explore",
                                style = MaterialTheme.typography.headlineSmall,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )

                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                                FeatureCard(
                                    title = "Live Schedule",
                                    subtitle = "Today's events",
                                    iconRes = null,
                                    containerColor = Color(0xFFFFE0B2) // warm orange container
                                ) { navController.navigate(Screen.Schedule.route) }

                                FeatureCard(
                                    title = "Live Schedule",
                                    subtitle = "Today's events",
                                    iconRes = null,
                                    containerColor = Color(0xFFFFE0B2) // warm orange container
                                ) { navController.navigate(Screen.Schedule.route) }

                            }
                            Spacer(modifier = Modifier.height(12.dp))
                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                                FeatureCard(
                                    title = "Stories / Gallery",
                                    subtitle = "Festival highlights",
                                    iconRes = null,
                                    containerColor = Color(0xFFE1BEE7) // lavender
                                ) { navController.navigate(Screen.Gallery.route) }


                                FeatureCard(
                                    title = "Booking Tickets",
                                    subtitle = "Entry & Participation",
                                    iconRes = null,
                                    containerColor = Color(0xFFC8E6C9) // mint green
                                ) { navController.navigate(Screen.FairEntryBooking.route) }
                            }
                        }
                    }

                    item {
                        SupportSection(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            onEmailTap = { /* open email Intent */ },
                            onCallTap = { /* open dialer */ },
                            onMessageTap = { /* open support message */ }
                        )
                    }

                    item {
                        Spacer(modifier = Modifier.height(80.dp))
                    }
                }

                BottomNavBarHome(
                    onHome = { /* already here */ },
                    onSchedule = { navController.navigate(Screen.Schedule.route) },
                    onMap = { navController.navigate(Screen.Map.route) },
                    onProfile = { navController.navigate(Screen.Profile.route) }
                )
            }
        }
    }
}

@Composable
private fun WelcomeSection(userDisplayName: String?) {
    val now = LocalTime.now()
    val greeting = when {
        now.hour < 12 -> "Good Morning"
        now.hour < 17 -> "Good Afternoon"
        else -> "Good Evening"
    }
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .shadow(8.dp, RoundedCornerShape(20.dp)),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "$greeting${if (userDisplayName != null) ", $userDisplayName" else ""}",
                style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Welcome to Namma Jatre — Celebrating village fairs and cultural pride. Learn, participate and enjoy!",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(12.dp))
            var showMission by remember { mutableStateOf(true) }
            AnimatedVisibility(visible = showMission, enter = androidx.compose.animation.fadeIn(animationSpec = tween(600))) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(text = "Our Mission", style = MaterialTheme.typography.titleMedium)
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(text = "Preserve and promote local traditions; create a safe, joyful festival experience for all.", style = MaterialTheme.typography.bodySmall)
                    }
                }
            }
        }
    }
}