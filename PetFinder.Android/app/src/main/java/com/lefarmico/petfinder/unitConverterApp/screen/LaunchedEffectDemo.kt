package com.lefarmico.petfinder.unitConverterApp.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive

@Composable
fun LaunchedEffectDemo() {
    var clickCount by rememberSaveable { mutableStateOf(0) }
    var counter by rememberSaveable { mutableStateOf(0) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
            Button(onClick = {
                clickCount++
            }) {
                Text(
                    text = if (clickCount == 0) "Start" else "Restart"
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                enabled = clickCount > 0,
                onClick = {
                    clickCount = 0
                }
            ) {
                Text(text = "Stop")
            }
            if (clickCount > 0) {
                DisposableEffect(clickCount) {
                    println("init: clickCount is $clickCount")
                    onDispose {
                        println("dispose: clickCount is $clickCount")
                    }
                }
                LaunchedEffect(clickCount) {
                    counter = 0
                    while (isActive) {
                        counter++
                        delay(1000)
                    }
                }
            }
        }
        Text(
            text = "$counter",
            style = MaterialTheme.typography.h3
        )
    }
}
