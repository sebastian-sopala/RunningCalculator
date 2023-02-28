package com.example.runningcalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
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
import com.example.runningcalculator.ui.theme.RunningCalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {}
        }
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit) {
    RunningCalculatorTheme {
// A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .padding(top = 24.dp),
            color = Color.LightGray,
            shape = CircleShape.copy(CornerSize(12.dp))
        ) {
            val speedState = remember {
                mutableStateOf("")
            }

            val minState = remember {
                mutableStateOf("")
            }

            val secState = remember {
                mutableStateOf("")
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(start = 30.dp, end = 30.dp)
                        .fillMaxWidth()
                ) {
                    Text(text = "Tempo : ")
                    InputField(
                        valueState = minState, label = "min"
                    )
                    Text(
                        modifier = Modifier.padding(4.dp), text = ":", fontWeight = FontWeight.Bold
                    )
                    InputField(
                        valueState = secState, label = "sec"
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(start = 30.dp, end = 30.dp)
                        .fillMaxWidth()
                ) {
                    Text(text = "Speed : ")
                    InputField(
                        valueState = speedState, label = "km/h"
                    )
                }
                Button(text = "CALCULATE", modifier = Modifier.padding(top = 24.dp)) {
                    if(minState.value.isNotEmpty() || secState.value.isNotEmpty()) {
                        speedState.value = timeToSpeed(minState, secState)
                    }
                    if(speedState.value.isNotEmpty()) {
                        minState.value = speedToTime(speedState)[0]
                        secState.value = speedToTime(speedState)[1]
                    } else {} // TODO: is this else needed??
                }
                Button(text = "CLEAR", modifier = Modifier.padding(top = 24.dp)) {
                    speedState.value = ""
                    minState.value = ""
                    secState.value = ""

                }
            }
        }
    }
}

@Composable
fun InputField(
    modifier: Modifier = Modifier,
    valueState: MutableState<String>,
    label: String = "",
    keyboardType: KeyboardType = KeyboardType.Number,
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default,
//    placeholderText: String = "0",
//    leadingIcon: ImageVector
) {
    OutlinedTextField(
        modifier = Modifier.width(100.dp),
        textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
        value = valueState.value,
        keyboardActions = onAction,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        onValueChange = { valueState.value = it },
        label = { Text(text = label) },
//        leadingIcon = { Icon(imageVector = leadingIcon, contentDescription = "icon")},
//        placeholder = { Text(text = placeholderText) },
        singleLine = true,

        )

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Button(
    modifier: Modifier = Modifier, text: String, onClick: () -> Unit
) {
    Surface(
        modifier = modifier,
        shape = CircleShape.copy(CornerSize(6.dp)),
        color = Color.DarkGray,
        onClick = onClick
    ) {
        Text(
            text = text, modifier = Modifier.padding(10.dp), color = Color.LightGray
        )
    }
}


fun timeToSpeed(min: MutableState<String>, sec: MutableState<String>): String {
    if (min.value.isEmpty()) {
        min.value = "0"
    }

    if (sec.value.isEmpty()) {
        sec.value = "0"
    }

    val result = 3600 / (min.value.toDouble() * 60 + sec.value.toDouble())

    return String.format("%.1f", result)
}


fun speedToTime(speed: MutableState<String>): ArrayList<String> {
    if(speed.value.isEmpty()) {
        speed.value = "0"
    }

    val sum = 3600 / speed.value.toDouble()
    val min = (sum / 60)
    val sec = (sum % 60)

    return arrayListOf(String.format("%02.0f", min), String.format("%02.0f", sec))
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RunningCalculatorTheme {
        MyApp() {}
    }
}