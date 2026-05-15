// app/src/main/java/com/example/nammajatrestatepride/ui/components/SupportSection.kt
package com.example.nammajatrestatepride.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Message
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment

@Composable
fun SupportSection(
    modifier: Modifier = Modifier,
    onEmailTap: () -> Unit,
    onCallTap: () -> Unit,
    onMessageTap: () -> Unit
) {
    Card(modifier = modifier, shape = RoundedCornerShape(12.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Support", style = androidx.compose.material3.MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(8.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
                    Button(onClick = onEmailTap) {
                        Icon(Icons.Default.Email, contentDescription = "Email")
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Email")
                    }
                }
                Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
                    Button(onClick = onCallTap) {
                        Icon(Icons.Default.Phone, contentDescription = "Call")
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Call")
                    }
                }
                Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
                    Button(onClick = onMessageTap) {
                        Icon(Icons.Default.Message, contentDescription = "Message")
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Message")
                    }
                }
            }
        }
    }
}