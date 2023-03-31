package com.example.runningcalculator.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.runningcalculator.DetailScreen
import com.example.runningcalculator.HomeScreen
import com.example.runningcalculator.data.RecordDataSource
import com.example.runningcalculator.screens.Screen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val list = RecordDataSource().loadRecordList()
    NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {
        composable(route = Screen.HomeScreen.route) {
            HomeScreen(navController = navController, records = list)
        }
        composable(
            route = Screen.DetailScreen.route + "/{name}" ,
            arguments = listOf(
                navArgument("name") {
                    type = NavType.StringType
                    defaultValue = "Default Value"
                    nullable = false
                }
            )
        ) {
            DetailScreen(text = it.arguments?.getString("name", "default value"))
        }
    }
}