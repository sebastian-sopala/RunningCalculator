package com.example.runningcalculator

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddRoad
import androidx.compose.material.icons.rounded.Speed
import androidx.compose.material.icons.rounded.Timer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.runningcalculator.components.*
import com.example.runningcalculator.data.RecordDataSource
import com.example.runningcalculator.model.Record
import com.example.runningcalculator.navigation.Navigation
import com.example.runningcalculator.screens.Screen
import com.example.runningcalculator.ui.*
import com.example.runningcalculator.ui.theme.RunningCalculatorTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Navigation()
        }
    }
}

@Composable
fun HomeScreen(navController: NavController, records: List<Record>) {

    val speedState = remember {
        mutableStateOf("")
    }

    val tempoMinState = remember {
        mutableStateOf("")
    }

    val tempoSecState = remember {
        mutableStateOf("")
    }

    val distState = remember {
        mutableStateOf("")
    }

    val timeMinState = remember {
        mutableStateOf("")
    }

    val timeSecState = remember {
        mutableStateOf("")
    }


    RunningCalculatorTheme(darkTheme = false) {
        Scaffold(
            topBar = {
                TopAppBar(
                    backgroundColor = Color.DarkGray,
                    contentColor = Color.LightGray
                ) {
                    Text(text = "Top Bar")
                }
            },
            bottomBar = {
                BottomAppBar(
                    backgroundColor = Color.DarkGray,
                    contentColor = Color.LightGray
                ) {
                    Text(text = "Bottom Bar")
                }
            },
            modifier = Modifier.padding(8.dp)
        ) {
            // speed to tempo calculator
            Column() {
                CalculatorRow(
                    icon = Icons.Rounded.Timer,
                    contentDescription = "tempo",
                    title = "Tempo",
                    firstLabel = "min",
                    secondLabel = "sec",
                    twoInputFields = true,
                    firstInputState = tempoMinState,
                    secondInputState = tempoSecState,
                )
                CalculatorRow(
                    icon = Icons.Rounded.Speed,
                    contentDescription = "speed",
                    title = "Speed",
                    firstLabel = "km/h",
                    secondLabel = null,
                    twoInputFields = false,
                    firstInputState = speedState,
                    secondInputState = null,
                )
                CalculatorRow(
                    icon = Icons.Rounded.Timer,
                    contentDescription = "time",
                    title = "Time",
                    firstLabel = "min",
                    secondLabel = "sec",
                    twoInputFields = true,
                    firstInputState = timeMinState,
                    secondInputState = timeSecState,
                )
                CalculatorRow(
                    icon = Icons.Rounded.AddRoad,
                    contentDescription = "distance",
                    title = "Dist.",
                    firstLabel = "km",
                    secondLabel = null,
                    twoInputFields = false,
                    firstInputState = distState,
                    secondInputState = null,
                )
                SmthngToClick() {
                    // TODO - remove
                    Log.d("TestTag", "testmsg: $it")
                    navController.navigate(Screen.DetailScreen.withArgs(it))
                }
                Row(horizontalArrangement = Arrangement.SpaceAround) {//SpaceAround not working ??
                    MyButton(text = "Calculate") {
                        if (tempoMinState.value.isNotEmpty() || tempoSecState.value.isNotEmpty()) {
                            speedState.value = timeToSpeed(tempoMinState, tempoSecState)
                        }
                        if (speedState.value.isNotEmpty()) {
                            tempoMinState.value = speedToTime(speedState)[0]
                            tempoSecState.value = speedToTime(speedState)[1]
                        }
                        if (speedState.value.isNotEmpty() && timeMinState.value.isNotEmpty() || timeSecState.value.isNotEmpty()) {
                            distState.value =
                                speedAndTimeToDistance(speedState, timeMinState, timeSecState)
                        }
                        if (speedState.value.isNotEmpty() && distState.value.isNotEmpty()) {
                            timeMinState.value = distAndSpeedToTime(distState, speedState)[0]
                            timeSecState.value = distAndSpeedToTime(distState, speedState)[1]
                        }

                        if (timeMinState.value.isNotEmpty() || timeSecState.value.isNotEmpty() && distState.value.isNotEmpty()) {
                            speedState.value =
                                speedFromDistanceAndTime(distState, timeMinState, timeSecState)
                        }
                    }
                    MyButton(text = "Add") {
                        val newRecord = Record(
                            tempo = "$tempoMinState:$tempoSecState",
                            speed = "$speedState",
                            time = "$timeMinState:$timeSecState ",
                            distance = "$distState"
                        )

                        list.
                    }
                    MyButton(text = "Clear") {
                        speedState.value = ""
                        tempoMinState.value = ""
                        tempoSecState.value = ""
                        distState.value = ""
                        timeMinState.value = ""
                        timeSecState.value = ""
                    }
                }
                LazyColumn {
                    items(records) { record ->
                        RecordRow(
                            tempo = record.tempo,
                            speed = record.speed,
                            time = record.time,
                            distance = record.distance,
                            description = record.description
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun DetailScreen(text: String?) {
    Text(text = "Hello $text")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RunningCalculatorTheme {
        Navigation()
    }
}