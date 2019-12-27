package android.weatherapp.repository

import android.weatherapp.model.RecentCity
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RecentCityDao {

    @Insert
    fun addCityWeather(recentCity: RecentCity)

    @Query("SELECT  * FROM recentcity ORDER BY updated_time ASC LIMIT 10")
    fun viewRecentSearch(): List<RecentCity>

}