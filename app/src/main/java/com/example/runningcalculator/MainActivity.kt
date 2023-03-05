package com.example.runningcalculator

import android.graphics.drawable.Icon
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.runningcalculator.ui.*
import com.example.runningcalculator.ui.theme.RunningCalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}

// TODO: clear -> focus off

@Composable
fun MyApp() {
    RunningCalculatorTheme (darkTheme = false) {
// A surface container using the 'background' color from the theme

        val speedState = remember {
            mutableStateOf("")
        }

        val minState = remember {
            mutableStateOf("")
        }

        val secState = remember {
            mutableStateOf("")
        }

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .padding(top = 24.dp),
            color = Color.LightGray,
            shape = CircleShape.copy(CornerSize(12.dp))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(start = 20.dp, end = 20.dp)
                        .fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.weight(2f),
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Row() {
                            Icon(
                                imageVector = Icons.Rounded.Timer,
                                contentDescription = "timer icon",
                                modifier = Modifier.padding(end = 8.dp)
                            )
                            Text(
                                text = "Tempo : ", fontWeight = FontWeight.Bold, fontSize = 20.sp
                            )
                        }

                    }

                    Column(
                        modifier = Modifier.weight(4f),
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            InputField(
                                valueState = minState, label = "min"
                            )
                            Text(
                                modifier = Modifier.padding(4.dp),
                                text = ":",
                                fontWeight = FontWeight.Bold,
                                fontSize = 24.sp
                            )
                            InputField(
                                valueState = secState, label = "sec"
                            )
                        }
                    }
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(start = 20.dp, end = 20.dp)
                        .fillMaxWidth()
                ) {
                    Column(modifier = Modifier.weight(2f)) {
                        Row() {
                            Icon(
                                imageVector = Icons.Rounded.Speed,
                                contentDescription = "speed icon",
                                modifier = Modifier.padding(end = 8.dp)
                            )
                            Text(
                                text = "Speed : ", fontWeight = FontWeight.Bold, fontSize = 20.sp
                            )
                        }

                    }
                    Column(modifier = Modifier.weight(4f)) {
                        InputField(
                            valueState = speedState, label = "km/h"
                        )
                    }
                }
                myButton(
                    text = "CALCULATE", modifier = Modifier.padding(top = 24.dp)
                ) {
                    if (minState.value.isNotEmpty() || secState.value.isNotEmpty()) {
                        speedState.value = timeToSpeed(minState, secState)
                    }
                    if (speedState.value.isNotEmpty()) {
                        minState.value = speedToTime(speedState)[0]
                        secState.value = speedToTime(speedState)[1]
                    }
                }
                myButton(
                    text = "CLEAR", modifier = Modifier.padding(top = 24.dp)
                ) {
                    speedState.value = ""
                    minState.value = ""
                    secState.value = ""

                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RunningCalculatorTheme {
        MyApp()
    }
}