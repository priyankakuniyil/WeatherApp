package android.weatherapp.view

import android.os.Bundle
import android.util.Log
import android.weatherapp.R
import android.weatherapp.db.sqlite.DatabaseHelper
import android.weatherapp.viewmodel.SqliteViewModel
import android.weatherapp.viewmodel.WeatherDetailsViewModel
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.gson.Gson
import com.squareup.picasso.Picasso

class WeatherDetails : AppCompatActivity() {

    lateinit var img_back: ImageView
    lateinit var txt_city_name: TextView
    lateinit var img_weather: ImageView
    lateinit var txt_weather: TextView
    lateinit var tv_humidity: TextView
    lateinit var tv_temperature: TextView

    lateinit var s_cityName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.weather_details)

        s_cityName = intent.getStringExtra("city_name")

        val weatherDetailsViewModel =
            ViewModelProviders.of(this@WeatherDetails).get(WeatherDetailsViewModel::class.java)

        val sqliteViewModel =
            ViewModelProviders.of(this@WeatherDetails).get(SqliteViewModel::class.java)

        img_back = findViewById(R.id.img_back)
        txt_city_name = findViewById(R.id.txt_city_name)
        img_weather = findViewById(R.id.img_weather)
        txt_weather = findViewById(R.id.txt_weather)
        tv_humidity = findViewById(R.id.tv_humidity)
        tv_temperature = findViewById(R.id.tv_temperature)

        img_back.setOnClickListener {
            finish()
        }

        weatherDetailsViewModel.weatherDetails(s_cityName)
            .observe(this, Observer {

                Log.e("Weather Details", "${Gson().toJson(it)}")

                txt_city_name.text = it.data.request[0].query
                txt_weather.text = it.data.current_condition[0].weatherDesc[0].value
                tv_humidity.text = it.data.current_condition[0].humidity.toString()
                tv_temperature.text = it.data.current_condition[0].temp_C.toString() + "\u2103"

                Picasso.get()
                    .load(it.data.current_condition[0].weatherIconUrl[0].value)
                    .error(R.drawable.ic_cloud_queue_black_24dp)
                    .into(img_weather)

                sqliteViewModel.addCityWeather(DatabaseHelper(this), it)

            })

    }
}
