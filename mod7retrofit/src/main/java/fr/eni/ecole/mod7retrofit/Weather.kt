package fr.eni.ecole.mod7retrofit

import com.squareup.moshi.Json

data class WeatherResponse(
    val hourly: HourlyData
)

data class HourlyData(
    val time: List<String>,

    @Json(name = "temperature_2m")
    val temperatures: List<Double>,


)
