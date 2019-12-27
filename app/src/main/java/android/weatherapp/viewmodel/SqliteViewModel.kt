package android.weatherapp.viewmodel

import WeatherDetail
import android.weatherapp.db.sqlite.DatabaseHelper
import android.weatherapp.model.RecentCity
import androidx.lifecycle.ViewModel

class SqliteViewModel : ViewModel() {

    fun getRecentSearches(helper: DatabaseHelper?): List<RecentCity> {
        return helper!!.viewRecentSearch()
    }

    fun addCityWeather(helper: DatabaseHelper?, weather: WeatherDetail?): Long {
        return helper!!.addCityWeather(weather)
    }

    fun deleteEntries(helper: DatabaseHelper?) {
        return helper!!.delete()
    }

}