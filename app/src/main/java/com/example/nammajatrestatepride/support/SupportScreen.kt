package com.example.nammajatrestatepride.support

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun SupportScreen(viewModel: SupportViewModel = hiltViewModel()) {
    var feedback by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text("🆘 Support & Help", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(24.dp))
        OutlinedTextField(value = feedback, onValueChange = { feedback = it }, label = { Text("Feedback") })
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { viewModel.submitFeedback(feedback); feedback = "" }) { Text("Submit") }
        // Add helpline, email buttons
    }
}