// app/src/main/java/com/example/nammajatrestatepride/ui/components/TopAppBarHome.kt
package com.example.nammajatrestatepride.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.fadeIn
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Badge
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.google.firebase.auth.FirebaseUser
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpOffset

@Composable
fun TopAppBarHome(
    user: FirebaseUser?,
    onSignInClick: () -> Unit,
    onRegisterClick: () -> Unit,
    onProfileClick: () -> Unit,
    onOpenSettings: () -> Unit
) {
    var searchExpanded by remember { mutableStateOf(false) }
    var searchQuery by remember { mutableStateOf("") }
    val suggestions = listOf("Temple Festivals", "Music Events", "Food Stalls", "Cultural Programs")

    var notificationsOpen by remember { mutableStateOf(false) }
    val notifications = remember {
        mutableStateListOf(
            NotificationItem("Parade Schedule Updated", "Parade now at 6:00 PM", "2h"),
            NotificationItem("New Photo Added", "Gallery updated with Yakshagana photos", "1d")
        )
    }
    val unreadCount = notifications.count { !it.read }

    var menuExpanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Menu (hamburger)
            IconButton(onClick = { menuExpanded = true }) {
                Icon(Icons.Default.Menu, contentDescription = "Menu")
            }

            // Title
            Text(
                text = "NAMMA JATRE",
                style = MaterialTheme.typography.headlineSmall,
                maxLines = 1
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                // Search icon
                IconButton(onClick = { searchExpanded = !searchExpanded }) {
                    Icon(Icons.Default.Search, contentDescription = "Search")
                }

                // Notification bell with badge
                Box {
                    IconButton(onClick = { notificationsOpen = !notificationsOpen }) {
                        BadgedBox(badge = {
                            if (unreadCount > 0) {
                                Badge { Text(unreadCount.toString()) }
                            }
                        }) {
                            Icon(Icons.Default.Notifications, contentDescription = "Notifications")
                        }
                    }

                    DropdownMenu(
                        expanded = notificationsOpen,
                        onDismissRequest = { notificationsOpen = false },
                        offset = DpOffset(x = (-8).dp, y = 8.dp)
                    ) {
                        notifications.forEachIndexed { index, item ->
                            DropdownMenuItem(
                                text = {
                                    Column {
                                        Text(item.title, style = MaterialTheme.typography.titleMedium)
                                        Text(item.subtitle, style = MaterialTheme.typography.bodySmall)
                                    }
                                },
                                onClick = {
                                    notifications[index] = item.copy(read = true)
                                }
                            )
                        }
                        if (notifications.isEmpty()) {
                            DropdownMenuItem(text = { Text("No notifications") }, onClick = { })
                        }
                    }
                }

                Spacer(modifier = Modifier.width(8.dp))

                // Profile avatar or sign in/register indicator
                if (user != null) {
                    val photoUrl = user.photoUrl
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.primaryContainer)
                            .clickable { onProfileClick() },
                        contentAlignment = Alignment.Center
                    ) {
                        if (photoUrl != null) {
                            AsyncImage(model = photoUrl, contentDescription = "Avatar", modifier = Modifier.fillMaxSize().clip(CircleShape))
                        } else {
                            Text(
                                text = user.displayName?.take(1)?.uppercase() ?: "U",
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        }
                    }
                } else {
                    TextButton(onClick = onSignInClick) { Text("Sign In") }
                }
            }
        }

        // Animated search area
        AnimatedVisibility(visible = searchExpanded, enter = fadeIn(animationSpec = tween(250))) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
                    .animateContentSize()
            ) {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("Search events, places, stories...") },
                    trailingIcon = {
                        IconButton(onClick = { searchQuery = "" }) {
                            Icon(Icons.Default.Close, contentDescription = "Clear")
                        }
                    }
                )

                Spacer(modifier = Modifier.height(6.dp))

                Column(modifier = Modifier.fillMaxWidth()) {
                    suggestions.forEach { suggestion ->
                        Text(
                            text = suggestion,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    searchQuery = suggestion
                                }
                                .padding(vertical = 8.dp),
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Divider()
                    }
                }
            }
        }

        // Menu dropdown below top bar
        DropdownMenu(expanded = menuExpanded, onDismissRequest = { menuExpanded = false }, offset = DpOffset(x = 0.dp, y = 8.dp)) {
            DropdownMenuItem(text = { Text("Settings") }, onClick = {
                onOpenSettings(); menuExpanded = false
            })
            DropdownMenuItem(text = { Text("Guide") }, onClick = { menuExpanded = false })
            DropdownMenuItem(text = { Text("Profile") }, onClick = { onProfileClick(); menuExpanded = false })
            DropdownMenuItem(text = { Text("FAQ") }, onClick = { menuExpanded = false })
            DropdownMenuItem(text = { Text("Events") }, onClick = { menuExpanded = false })
            DropdownMenuItem(text = { Text("Tickets") }, onClick = { menuExpanded = false })
            DropdownMenuItem(text = { Text("Gallery") }, onClick = { menuExpanded = false })
            if (user == null) {
                DropdownMenuItem(text = { Text("Sign In") }, onClick = { onSignInClick(); menuExpanded = false })
            }
        }
    }
}

private data class NotificationItem(val title: String, val subtitle: String, val timeAgo: String, val read: Boolean = false)