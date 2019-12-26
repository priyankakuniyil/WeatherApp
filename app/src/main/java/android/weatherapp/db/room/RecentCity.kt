package android.weatherapp.repository

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "RecentCity")
class RecentCity(
    @PrimaryKey(autoGenerate = true)
    var city_id: Int,
    var city_name: String,
    var city_weather: String,
    var city_weather_image: String,
    var city_humidity: String,
    var city_temperature: String,
    var updated_time: String
)