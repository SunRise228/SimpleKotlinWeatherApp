package com.example.weatherappwithvova.customenum

import com.example.weatherappwithvova.R

class WeatherConditionCollection {
    fun getWeatherConditionByCode(countryCode : Int) : String {
        return when (countryCode) {
            0 -> "Ясно"
            1 -> "Преимущественно ясно"
            2 -> "переменная облачность"
            3 -> "Пасмурно"
            45,48 -> "Туман"
            51 -> "Легкая морось"
            53 -> "Умеренная морось"
            55 -> "Сильная морось"
            56,57 -> "Изморось"
            61,80 -> "Незначительный дождь"
            63,81 -> "Умеренный дождь"
            65,82 -> "Ливень"
            66,67 -> "Ледяной дождь"
            71,85 -> "Небольшой снегопад"
            73 -> "Умеренный снегопад"
            75,86 -> "Сильный снегопад "
            77 -> "Град"
            95 -> "Гроза"
            96,99 -> "Гроза с градом"
            else -> "Неизвестно"
        }
    }
}