package android.weatherapp.viewmodel

import WeatherDetail
import android.weatherapp.db.retrofit.ProjectRepository
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WeatherDetailsViewModel : ViewModel() {

    var weather = MutableLiveData<WeatherDetail>()

    fun weatherDetails(cityName: String): MutableLiveData<WeatherDetail> {
        weather = ProjectRepository().getWeatherDetails(cityName)
        return weather
    }

}