package com.example.runningcalculator.ui

import androidx.compose.runtime.MutableState

fun speedToTime(speed: MutableState<String>): ArrayList<String> {
    if (speed.value.isEmpty()) {
        speed.value = "0"
    }

    val sum = 3600 / speed.value.toDouble()
    val min = (sum / 60)
    val sec = (sum % 60)

    return arrayListOf(String.format("%02.0f", min), String.format("%02.0f", sec))
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