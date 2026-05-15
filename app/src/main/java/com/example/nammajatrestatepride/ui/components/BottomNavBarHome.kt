// app/src/main/java/com/example/nammajatrestatepride/ui/components/BottomNavBarHome.kt
package com.example.nammajatrestatepride.ui.components

import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.Modifier

@Composable
fun BottomNavBarHome(
    modifier: Modifier = Modifier,
    onHome: () -> Unit,
    onSchedule: () -> Unit,
    onMap: () -> Unit,
    onProfile: () -> Unit
) {
    NavigationBar(modifier = modifier) {
        NavigationBarItem(selected = true, onClick = { onHome() }, icon = { Icon(Icons.Default.Home, contentDescription = "Home") }, label = { Text("Home") })
        NavigationBarItem(selected = false, onClick = { onSchedule() }, icon = { Icon(Icons.Default.Schedule, contentDescription = "Schedule") }, label = { Text("Schedule") })
        NavigationBarItem(selected = false, onClick = { onMap() }, icon = { Icon(Icons.Default.Map, contentDescription = "Map") }, label = { Text("Map") })
        NavigationBarItem(selected = false, onClick = { onProfile() }, icon = { Icon(Icons.Default.Person, contentDescription = "Profile") }, label = { Text("Profile") })
    }
}