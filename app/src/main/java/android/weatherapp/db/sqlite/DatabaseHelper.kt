package android.weatherapp.db.sqlite

import WeatherDetail
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.weatherapp.model.RecentCity
import android.weatherapp.util.currentDate

class DatabaseHelper(context: Context?) :
    SQLiteOpenHelper(
        context,
        DATABASE_NAME, null,
        DATABASE_VERSION
    ) {

    companion object {
        private val DATABASE_VERSION = 1

        private val DATABASE_NAME = "WeatherApp"
        private val TABLE_RECENT_SEARCH = "RecentSearch"

        private val KEY_CITY_NAME = "city_name"
        private val KEY_CITY_WEATHER = "city_weather"
        private val KEY_CITY_WEATHER_IMAGE = "city_weather_image"
        private val KEY_CITY_HUMIDITY = "city_humidity"
        private val KEY_CITY_TEMPERATURE = "city_temperature"
        private val KEY_UPDATED_TIME = "updated_time"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE = ("CREATE TABLE " + TABLE_RECENT_SEARCH + "("
                + KEY_CITY_NAME + " TEXT PRIMARY KEY,"
                + KEY_CITY_WEATHER + " TEXT,"
                + KEY_CITY_WEATHER_IMAGE + " TEXT,"
                + KEY_CITY_HUMIDITY + " TEXT,"
                + KEY_CITY_TEMPERATURE + " TEXT,"
                + KEY_UPDATED_TIME + " DATE" + ")")

        db?.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    fun addCityWeather(weather: WeatherDetail): Long {

        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_CITY_NAME, weather.data.request[0].query)
        contentValues.put(KEY_CITY_WEATHER, weather.data.current_condition[0].weatherDesc[0].value)
        contentValues.put(
            KEY_CITY_WEATHER_IMAGE,
            weather.data.current_condition[0].weatherIconUrl[0].value
        )
        contentValues.put(KEY_CITY_HUMIDITY, weather.data.current_condition[0].humidity.toString())
        contentValues.put(
            KEY_CITY_TEMPERATURE,
            weather.data.current_condition[0].temp_C.toString() + "\u2103"
        )
        contentValues.put(KEY_UPDATED_TIME, currentDate())

        val success = db.replace(TABLE_RECENT_SEARCH, null, contentValues)

        db.close()

        return success

    }

    fun viewRecentSearch(): List<RecentCity> {

        val weatherList: ArrayList<RecentCity> = ArrayList()
        val selectQuery =
            "SELECT  * FROM $TABLE_RECENT_SEARCH ORDER BY $KEY_UPDATED_TIME DESC LIMIT 10"
        val db = this.readableDatabase
        var cursor: Cursor
        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }

        var cityName: String
        var cityWeather: String
        var cityWeatherImage: String
        var cityHumidity: String
        var cityTemperature: String
        var UpdatedTime: String

        if (cursor.moveToFirst()) {
            do {

                cityName = cursor.getString(cursor.getColumnIndex(KEY_CITY_NAME))
                cityWeather = cursor.getString(cursor.getColumnIndex(KEY_CITY_WEATHER))
                cityWeatherImage = cursor.getString(cursor.getColumnIndex(KEY_CITY_WEATHER_IMAGE))
                cityHumidity = cursor.getString(cursor.getColumnIndex(KEY_CITY_HUMIDITY))
                cityTemperature = cursor.getString(cursor.getColumnIndex(KEY_CITY_TEMPERATURE))
                UpdatedTime = cursor.getString(cursor.getColumnIndex(KEY_UPDATED_TIME))

                val emp =
                    RecentCity(
                        cityName = cityName,
                        cityWeather = cityWeather,
                        cityWeatherImage = cityWeatherImage,
                        cityHumidity = cityHumidity,
                        cityTemperature = cityTemperature,
                        updated_time = UpdatedTime
                    )
                weatherList.add(emp)

            } while (cursor.moveToNext())
        }

        return weatherList

    }

}