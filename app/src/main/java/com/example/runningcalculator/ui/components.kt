package com.example.runningcalculator.ui

import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

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