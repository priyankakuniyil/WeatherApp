package android.weatherapp.viewmodel

import WeatherDetail
import android.content.Context
import android.weatherapp.db.sqlite.DatabaseHelper
import android.weatherapp.model.RecentCity
import androidx.lifecycle.ViewModel

class SqliteViewModel : ViewModel() {

    fun getRecentSearches(context: Context?): List<RecentCity> {
        return DatabaseHelper(context).viewRecentSearch()
    }

    fun addCityWeather(context: Context, weather: WeatherDetail): Long {
        return DatabaseHelper(context).addCityWeather(weather)
    }

}