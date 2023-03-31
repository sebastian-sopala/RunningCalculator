package com.example.runningcalculator.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Timer
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

@Composable
fun CalculatorRow(
    icon: ImageVector,
    contentDescription: String,
    title: String,
    firstLabel: String,
    secondLabel: String?,
    twoInputFields: Boolean,
    firstInputState: MutableState<String>,
    secondInputState: MutableState<String>?,
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
                    imageVector = icon,
                    contentDescription = contentDescription,
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text(
                    text = "$title : ",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
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
                    valueState = firstInputState, label = firstLabel
                )
                if (twoInputFields) {
                    Text(
                        modifier = Modifier.padding(4.dp),
                        text = ":",
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    )
                    InputField(
                        valueState = secondInputState ?: "" as MutableState<String>,
                        label = secondLabel ?: "" // if(secondLabel != null) secondLabel else ""
                    )
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
) {
    OutlinedTextField(
        modifier = Modifier.widthIn(50.dp, 100.dp),
        textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
        value = valueState.value,
        keyboardActions = onAction,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        onValueChange = { valueState.value = it },
        label = { Text(text = label) },
        singleLine = true,
    )

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MyButton(
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
fun RecordRow(tempo: String, speed: String, time: String, distance: String, description: String?) {
    Surface() {
        Column() {
            Row() {
                Column(Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Tempo", fontWeight = FontWeight.Bold)
                    Text(text = tempo)
                }
                Column(Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Speed", fontWeight = FontWeight.Bold)
                    Text(text = speed)
                }
                Column(Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Time", fontWeight = FontWeight.Bold)
                    Text(text = time)
                }
                Column(Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Distance", fontWeight = FontWeight.Bold)
                    Text(text = distance)
                }
            }
            if (description != null) {
                Row() {
                    Text(text = description)
                }
            }
        }
    }
}

@Preview(backgroundColor = 2002002008)
@Composable
fun CalculatorRowPreview() {
    Column() {
        CalculatorRow(
            icon = Icons.Rounded.Timer,
            contentDescription = "",
            title = "Tempo",
            firstLabel = "min",
            secondLabel = "sec",
            twoInputFields = true,
            firstInputState = "123" as MutableState<String>,
            secondInputState = "123" as MutableState<String>,
        )
        CalculatorRow(
            icon = Icons.Rounded.Timer,
            contentDescription = "",
            title = "Speed",
            firstLabel = "km/h",
            secondLabel = null,
            twoInputFields = false,
            firstInputState = "123" as MutableState<String>,
            secondInputState = null,
        )
    }
}

@Preview(backgroundColor = 2002002008)
@Composable
fun RecordRowPreview() {
    RecordRow(
        tempo = "6:15",
        speed = "5.25",
        time = "23:15",
        distance = "7.1",
        description = "jjahsgvcasgfiuvgakdjf uh kausgcjbaskjdhvciage qkeurhbck nasiugfiuhe rv"
    )

}