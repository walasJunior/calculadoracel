package com.example.calculadoracel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.calculadoracel.ui.theme.CalculadoracelTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculadoracelTheme() {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    CalculadoraScreen()
                }
            }
        }
    }
}

@Composable
fun CalculadoraScreen() {
    var value1 by remember { mutableStateOf("")}
    var value2 by remember { mutableStateOf("")}
    var operator by remember { mutableStateOf("")}
    var result by remember { mutableStateOf("")}

    Column(Modifier.padding(16.dp)){
        TextField(
            value = value1,
            onValueChange = {value1 = it},
            label = { Text(text = "value 1")},
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            ),
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = value2,
            onValueChange = {value2 = it},
            label = { Text(text = "value 2")},
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            ),
            modifier = Modifier.fillMaxWidth()
        )
        Row(
            Modifier
                .padding(vertical = 16.dp)
                .align(CenterHorizontally)) {

            Button(onClick = { operator = "+"}, modifier = Modifier.padding(5.dp)){
                Text(text = "+")
            }
            Button(onClick = { operator = "-"}, modifier = Modifier.padding(5.dp)){
                Text(text = "-")
            }
            Button(onClick = { operator = "*"}, modifier = Modifier.padding(5.dp)){
                Text(text = "*")
            }
            Button(onClick = { operator = "/"}, modifier = Modifier.padding(5.dp)){
                Text(text = "/")
            }
            Button(modifier = Modifier.padding(5.dp),onClick = {
                if (value1.isNotEmpty() && value2.isNotEmpty() && operator.isNotEmpty()){
                    result = when (operator){
                        "+" -> (value1.toDouble() + value2.toDouble()).toString()
                        "-" -> (value1.toDouble() - value2.toDouble()).toString()
                        "*" -> (value1.toDouble() * value2.toDouble()).toString()
                        "/" -> (value1.toDouble() / value2.toDouble()).toString()
                        else -> ""
                    }
                }
            }) {
                Text(text ="=")
            }
        }

        Button(modifier = Modifier.padding(5.dp), onClick = {
            value1 = ""
            value2 = ""
            operator = ""
            result = ""
        }) {
            Text(text = "Clear")
        }
        if (result.isNotEmpty()){
            Text(text = "Result: $result", Modifier.padding(vertical = 16.dp))
        }
    }
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview(){
    CalculadoraScreen()
}