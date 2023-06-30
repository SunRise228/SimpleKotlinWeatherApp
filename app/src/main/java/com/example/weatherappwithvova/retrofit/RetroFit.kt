package com.example.weatherappwithvova.retrofit

import com.example.weatherappwithvova.daoclass.city.CityDTO
import com.example.weatherappwithvova.daoclass.forecast.ForecastDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroFit {

    @GET("v1/dwd-icon?latitude=34.05&longitude=-118.24&hourly=temperature_2m,weathercode,windspeed_10m&daily=weathercode,temperature_2m_max,temperature_2m_min&timezone=GMT")
    fun getForecastJson(
        @Query("latitude") latitude: Double ,
        @Query("longitude") longitude: Double ,

    ): Call<ForecastDTO>

    @GET("v1/search?name=Minsk")
    fun getCityJson(
        @Query("name") name: String
    ): Call<CityDTO>

}