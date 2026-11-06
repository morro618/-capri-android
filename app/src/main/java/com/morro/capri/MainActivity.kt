package com.morro.capri

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CapriApp()
        }
    }
}

@Composable
fun CapriApp() {
    var input by remember { mutableStateOf("") }

    val messages = remember {
        mutableStateListOf(
            "Capri: Hello! I'm Capri, your AI assistant."
        )
    }

    val background = Color(0xFF101018)
    val panel = Color(0xFF20202D)
    val accent = Color(0xFFB64CFF)

    MaterialTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(background)
                .padding(16.dp)
        ) {
            Text(
                text = "CAPRI",
                color = accent,
                fontSize = 30.sp
            )

            Text(
                text = "Your personal AI assistant",
                color = Color.LightGray,
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(20.dp))

            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(messages) { message ->
                    Text(
                        text = message,
                        color = Color.White,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                panel,
                                RoundedCornerShape(12.dp)
                            )
                            .padding(14.dp)
                    )
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = input,
                    onValueChange = { input = it },
                    modifier = Modifier.weight(1f),
                    placeholder = {
                        Text("Message Capri...")
                    },
                    singleLine = true
                )

                Spacer(modifier = Modifier.width(8.dp))

                Button(
                    onClick = {
                        val message = input.trim()

                        if (message.isNotEmpty()) {
                            messages.add("You: $message")
                            messages.add(
                                "Capri: Your message was received. " +
                                "AI connection will be added next."
                            )
                            input = ""
                        }
                    }
                ) {
                    Text("Send")
                }
            }
        }
    }
}
