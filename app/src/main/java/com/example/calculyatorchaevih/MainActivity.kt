package com.example.calculyatorchaevih

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.calculyatorchaevih.ui.theme.CalculyatorChaevihTheme
import androidx.compose.foundation.layout.*
import androidx.compose.ui.unit.dp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculyatorChaevihTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TipScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun TipScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier
        .fillMaxSize()
        .padding(16.dp)) {
        Text(
            text = "Калькулятор чаевых",
            style = androidx.compose.material3.MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(12.dp))

    }
}

@Preview(showBackground = true)
@Composable
fun TipPreview() {
    CalculyatorChaevihTheme {                                            // показываем TipScreen в теме
        TipScreen()
    }
}
