package com.example.weatherappwithvova.adapter

import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.weatherappwithvova.customenum.WeatherConditionCollection
import com.example.weatherappwithvova.customenum.WeatherIconCollection
import com.example.weatherappwithvova.daoclass.forecast.ForecastDTO
import com.example.weatherappwithvova.dtoclass.ViewPagerListItem
import com.example.weatherappwithvova.screen.fragment.DaysFragment
import com.example.weatherappwithvova.screen.fragment.HoursFragment
import java.util.*
import kotlin.collections.ArrayList

class RecyclerViewAdapter(private val forecastDTO: ForecastDTO) {

    fun fill(requireActivity: FragmentActivity, viewPager: ViewPager2) {

        val hoursList = ArrayList<ViewPagerListItem>()
        val daysList = ArrayList<ViewPagerListItem>()
        val currentDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        val currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)

        for (index in forecastDTO.hourly.weathercode.indices) {
            var date = forecastDTO.hourly.time[index]
            var day = date.substring(8,10)
            if (day.startsWith("0")) {
                day = day.substring(1)
            }
            var hour = date.substring(11,13)
            if (hour.startsWith("0")) {
                hour = hour.substring(1)
            }
            if (day == currentDay.toString() && hour >= currentHour.toString() ) {
                hoursList.add(
                    ViewPagerListItem(
                        forecastDTO.hourly.time[index].substring(11),
                        WeatherConditionCollection().getWeatherConditionByCode(forecastDTO.hourly.weathercode[index]),
                        forecastDTO.hourly.temperature_2m[index].toString(),
                        WeatherIconCollection().getIconByWeatherCode(forecastDTO.hourly.weathercode[index])
                    )
                )
            }
        }

        for (index in forecastDTO.daily.weathercode.indices) {
            daysList.add(
                ViewPagerListItem(
                    forecastDTO.daily.time[index],
                    WeatherConditionCollection().getWeatherConditionByCode(forecastDTO.daily.weathercode[index]),
                    forecastDTO.daily.temperature_2m_min[index].toString() + "/" + forecastDTO.daily.temperature_2m_max[index].toString(),
                    WeatherIconCollection().getIconByWeatherCode(forecastDTO.daily.weathercode[index])
                )
            )
        }

        viewPager.adapter = ViewPagerAdapter(requireActivity, listOf(HoursFragment.newInstance(hoursList), DaysFragment.newInstance(daysList)))
    }


}
