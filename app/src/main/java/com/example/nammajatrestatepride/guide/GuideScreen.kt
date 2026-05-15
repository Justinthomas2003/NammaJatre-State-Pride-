package com.example.nammajatrestatepride.guide

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun GuideScreen() {
    LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        item { Text("📋 Guide & Rules", style = MaterialTheme.typography.headlineMedium) }
        item { Text("Dos: Respect culture, follow guidelines.") }
        item { Text("Don'ts: No littering, no prohibited items.") }
        // Add more content
    }
}