package com.example.runningcalculator

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
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
fun HomeScreen(navController: NavController) {
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
            Column {
                Calculator()
                SmthngToClick() {
                    // TODO - remove
                    Log.d("TestTag", "testmsg: $it")
                    navController.navigate(Screen.DetailScreen.withArgs(it))
                }
                RunningDiary()
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