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
import androidx.compose.material3.Slider
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.*
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.RadioButton
import androidx.compose.ui.Alignment
import androidx.compose.material3.Divider
import java.util.Locale

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
fun DemoSlider(sliderPosition: Float, onPositionChange: (Float) -> Unit ) {
    Column(modifier = Modifier.fillMaxWidth()) {   // размещаем элементы по вертикали
        //текущие проценты над слайдером
        Text(text = "Чаевые: ${sliderPosition.toInt()}%")

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("0")  // метка "0" слева

            Slider(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp),   // отступы между метками
                valueRange = 0f..25f,              // диапазон значений
                value = sliderPosition,            // текущая позиция бегунка
                onValueChange = { onPositionChange(it) }
            )

            Text("25")   // метка "25" справа
        }
    }
}
@Composable
fun TipScreen(modifier: Modifier = Modifier) {
    var billInput by remember { mutableStateOf("") } //сумма заказа
    var dishesInput by remember { mutableStateOf("") } //колво блюд
    var tipPercent by remember { mutableFloatStateOf(0f) } //процент чаевых
    val pinkBg = Color(0xFFFFE6F0) //цвет
    val options = listOf(3, 5, 7, 10) //список скидок

    // количество блюд в Int (если не число -> 0)
    val dishesCount = dishesInput.toIntOrNull() ?: 0
    // автоподбор процента скидки по заданным правилам
    val discountPercent = when {
        dishesCount in 1..2 -> 3
        dishesCount in 3..5 -> 5
        dishesCount in 6..10 -> 7
        dishesCount > 10 -> 10
        else -> 0
    }


    @Composable
    fun InputRow(label: String, value: String, onChange: (String) -> Unit) { //размещение элементов в стоку
        Row(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp), verticalAlignment = Alignment.CenterVertically) {
            Text(text = label, modifier = Modifier.weight(0.35f))
            Column(
                modifier = Modifier
                    .weight(0.65f)
                    .background(color = pinkBg, shape = RoundedCornerShape(8.dp))
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                //поле ввода
                OutlinedTextField(value = value, onValueChange = onChange, singleLine = true, modifier = Modifier.fillMaxWidth())
            }
        }
    }

    Column(modifier = modifier.fillMaxSize().padding(16.dp)) { //основная разметка экрана
        Text(text = "Калькулятор чаевых", style = androidx.compose.material3.MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(12.dp))

        InputRow(label = "Сумма заказа:", value = billInput, onChange = { billInput = it })
        InputRow(label = "Количество блюд:", value = dishesInput, onChange = { dishesInput = it })

        Spacer(modifier = Modifier.height(12.dp))

        Column(modifier = Modifier.fillMaxWidth()) {
            Text(text = "Чаевые: ${tipPercent.toInt()}%", modifier = Modifier.align(Alignment.CenterHorizontally))
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(top = 4.dp)) {
                Text(text = "0", modifier = Modifier.padding(start = 4.dp))
                Slider(modifier = Modifier.weight(1f).padding(horizontal = 8.dp), valueRange = 0f..25f, value = tipPercent, onValueChange = { tipPercent = it })
                Text(text = "25", modifier = Modifier.padding(end = 4.dp))
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(text = "Скидка:")
        Row(modifier = Modifier.fillMaxWidth().padding(top = 8.dp), verticalAlignment = Alignment.CenterVertically) {
            // теперь мы показываем опции и отмечаем ту, которая равна discountPercent
            options.forEach { opt ->
                Row(modifier = Modifier.weight(1f), verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(selected = (opt == discountPercent), onClick = { /* disabled - выбор программный */ }, enabled = false)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "$opt%")
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}



@Preview(showBackground = true)
@Composable
fun TipPreview() {
    CalculyatorChaevihTheme {                                            // показываем TipScreen в теме
        TipScreen()
    }
}
