package com.example.runningcalculator.ui

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Speed
import androidx.compose.material.icons.rounded.Timer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
fun myButton(
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

@Composable
fun SmthngToClick(content: String = "Something to click", onClick: (String) -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 50.dp)
            .padding(top = 12.dp)
            .clickable { onClick(content) },
        color = Color.LightGray,
        shape = CircleShape.copy(CornerSize(12.dp)),
    ) {
        Text(text = content)
    }
}

@Composable
fun Calculator() {
    // TODO: clear -> focus off

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

                // TODO - remove
                Log.d("TestTag", "testmsg: CALCULATE")
            }
            myButton(
                text = "CLEAR", modifier = Modifier.padding(top = 24.dp)
            ) {
                speedState.value = ""
                minState.value = ""
                secState.value = ""

                // TODO - remove
                Log.d("TestTag", "testmsg: CLEAR")
            }
        }
    }
}