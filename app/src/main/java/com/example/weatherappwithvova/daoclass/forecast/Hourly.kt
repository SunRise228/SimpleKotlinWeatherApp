package com.example.weatherappwithvova.daoclass.forecast

data class Hourly(
    val temperature_2m: List<Double>,
    val time: List<String>,
    val weathercode: List<Int>,
    val windspeed_10m: List<Double>
)