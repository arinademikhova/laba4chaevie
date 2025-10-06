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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.*
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Alignment

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
    var billInput by remember { mutableStateOf("") } //сумма заказа
    var dishesInput by remember { mutableStateOf("") } //колво блюд

    val pinkBg = Color(0xFFFFE6F0)

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Калькулятор чаевых",
            style = androidx.compose.material3.MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) { //строка на всю ширину
            Text(text = "Сумма заказа:", modifier = Modifier.weight(0.35f)) //label слева
            Spacer(modifier = Modifier.width(8.dp)) // небольшой отступ между label и полем
            Column(
                modifier = Modifier
                    .weight(0.65f)  // поле занимает оставшиеся ~65% ширины
                    .background(color = pinkBg, shape = RoundedCornerShape(8.dp))
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                OutlinedTextField(
                    value = billInput,
                    onValueChange = { billInput = it },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp)) // отступ 8dp между полями

        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Количество блюд:", modifier = Modifier.weight(0.35f))
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                modifier = Modifier
                    .weight(0.65f)
                    .background(color = pinkBg, shape = RoundedCornerShape(8.dp))
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                OutlinedTextField(
                    value = dishesInput,
                    onValueChange = { dishesInput = it },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

    }
}

@Preview(showBackground = true)
@Composable
fun TipPreview() {
    CalculyatorChaevihTheme {
        TipScreen()
    }
}
