package com.example.runningcalculator.components

import androidx.compose.runtime.MutableState

fun speedToTime(speed: MutableState<String>): ArrayList<String> {
    if (speed.value.isEmpty()) {
        // TODO
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

    return String.format("%2.1f", result)
}

fun speedAndTimeToDistance(
    speed: MutableState<String>,
    min: MutableState<String>,
    sec: MutableState<String>
): String {
    if (min.value.isEmpty()) {
        min.value = "0"
    }

    if (sec.value.isEmpty()) {
        sec.value = "0"
    }

    val timeInSeconds = min.value.toDouble() * 60 + sec.value.toDouble()
    val timeInHours = timeInSeconds / 3600

    val dist = speed.value.toDouble() * timeInHours

    return String.format("%2.1f", dist)
}

fun distAndSpeedToTime(
    distance: MutableState<String>,
    speed: MutableState<String>,
): ArrayList<String> {

    val time = (distance.value.toDouble() / speed.value.toDouble()) * 3600

//    val hrs = (time / 3600)
    val min = (time / 60)
    val sec = (time % 60)

    return arrayListOf(String.format("%02.0f", min), String.format("%02.0f", sec))
}

fun speedFromDistanceAndTime(
    distance: MutableState<String>,
    min: MutableState<String>,
    sec: MutableState<String>,
): String {
    if (min.value.isEmpty()) {
        min.value = "0"
    }

    if (sec.value.isEmpty()) {
        sec.value = "0"
    }

    val timeInSeconds = min.value.toDouble() * 60 + sec.value.toDouble()
    val speedInSeconds = distance.value.toDouble() / timeInSeconds
    val speedInHours =  speedInSeconds * 3600

    return String.format("%.1f", speedInHours)
}