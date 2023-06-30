package com.example.weatherappwithvova.retrofit

import android.content.Context
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.weatherappwithvova.adapter.CurrentCardAdapter
import com.example.weatherappwithvova.adapter.RecyclerViewAdapter
import com.example.weatherappwithvova.daoclass.city.CityDTO
import com.example.weatherappwithvova.daoclass.forecast.ForecastDTO
import com.example.weatherappwithvova.databinding.FragmentHoursBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL_CITY = "https://geocoding-api.open-meteo.com/"
const val BASE_URL_FORECAST = "https://api.open-meteo.com/"

class GettingDataFromRetroFit {

    private lateinit var hoursFragment : FragmentHoursBinding

    fun getCityList(requiredContext: Context, name: String,  searchBar : AutoCompleteTextView) {

        val retroFitBuilder = Retrofit.Builder()
            .baseUrl(BASE_URL_CITY)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val retroFit = retroFitBuilder.create(RetroFit::class.java)
        val json = retroFit.getCityJson(name)
        var cityDTO : CityDTO

        json.enqueue(object : Callback<CityDTO> {
            override fun onResponse(call: Call<CityDTO>, response: Response<CityDTO>) {
                if (response.isSuccessful) {
                    cityDTO = response.body()!!
                    val list = arrayListOf<String>()
                    for (item in cityDTO.results) {
                        list.add(item.name)
                        if(list.size > 5) {
                            break
                        }
                    }
                    searchBar.setAdapter(ArrayAdapter(requiredContext, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, list))
                    Log.d("INFO", cityDTO.toString())
                } else {
                    Log.d("API Error", "Failed to retrieve city list")
                }
            }

            override fun onFailure(call: Call<CityDTO>, t: Throwable) {
                Log.e("API Error", "Error occurred while fetching city list", t)
            }

        })
    }

    fun getForecast(
        text: String,
        requireActivity: FragmentActivity,
        viewPager: ViewPager2,
        listOfTextView: List<TextView>,
    ) {

        val retroFitBuilder = Retrofit.Builder()
            .baseUrl(BASE_URL_CITY)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val retroFit = retroFitBuilder.create(RetroFit::class.java)
        val json = retroFit.getCityJson(text)
        var cityDTO: CityDTO

        json.enqueue(object : Callback<CityDTO> {
            override fun onResponse(call: Call<CityDTO>, response: Response<CityDTO>) {
                if (response.isSuccessful) {
                    cityDTO = response.body()!!
                    if(cityDTO.results.isNotEmpty()) {
                        getInnerForecast(cityDTO, requireActivity, viewPager, listOfTextView)
                    }
                    Log.d("INFO", cityDTO.toString())
                } else {
                    Log.d("API Error", "Failed to retrieve city list")
                }
            }

            override fun onFailure(call: Call<CityDTO>, t: Throwable) {
                Log.e("API Error", "Error occurred while fetching city list", t)
            }

        })
    }

    fun getInnerForecast(
        cityDto: CityDTO,
        requireActivity: FragmentActivity,
        viewPager: ViewPager2,
        listOfTextView: List<TextView>,
    ) {

        val retroFitBuilder = Retrofit.Builder()
            .baseUrl(BASE_URL_FORECAST)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val retroFit = retroFitBuilder.create(RetroFit::class.java)
        val json = retroFit.getForecastJson(cityDto.results[0].latitude, cityDto.results[0].longitude)
        var forecastDTO : ForecastDTO

        json.enqueue(object : Callback<ForecastDTO> {
            override fun onResponse(call: Call<ForecastDTO>, response: Response<ForecastDTO>) {
                if (response.isSuccessful) {
                    forecastDTO = response.body()!!
                    RecyclerViewAdapter(forecastDTO).fill(requireActivity, viewPager)
                    CurrentCardAdapter(forecastDTO).fill(listOfTextView, cityDto.results[0].name)
                    Log.d("INFO", forecastDTO.latitude.toString() + " " + forecastDTO.longitude.toString())
                } else {
                    Log.d("API Error", "Failed to retrieve city list")
                }
            }

            override fun onFailure(call: Call<ForecastDTO>, t: Throwable) {
                Log.e("API Error", "Error occurred while fetching city list", t)
            }
        })
    }
}