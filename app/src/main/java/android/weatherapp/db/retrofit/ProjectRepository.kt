package android.weatherapp.db.retrofit

import SearchResult
import WeatherDetail
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ProjectRepository {

    val apiInterface: ApiInterface

    init {
        apiInterface = ApiInterface.create()
    }

    fun getCityNames(query: String?): MutableLiveData<SearchResult> {

        val data = MutableLiveData<SearchResult>()

        apiInterface.searchCity(ApiInterface.API_KEY, query, ApiInterface.RESPONSE_FORMAT)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ result ->

                data.value = result

                for (i in 0 until result.search_api.result.size) {
                    Log.e(
                        "Loop $i",
                        result.search_api.result[i].areaName[0].value + " " + result.search_api.result[i].country[0].value
                    )
                }

                Log.e("Result", "${Gson().toJson(result)}")

            }, { error ->
                Log.e("Error", "${error}")

            })

        return data

    }

    fun getWeatherDetails(cityName: String?): MutableLiveData<WeatherDetail> {

        val data = MutableLiveData<WeatherDetail>()

        apiInterface.weatherDetails(ApiInterface.API_KEY, cityName, ApiInterface.RESPONSE_FORMAT)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ result ->

                data.value = result

                Log.e("Weather Details", "${Gson().toJson(result)}")

            }, { error ->
                Log.e("Error", "${error}")

            })

        return data

    }

}