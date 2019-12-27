package android.weatherapp.db.retrofit

import SearchResult
import WeatherDetail
import io.reactivex.Flowable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    companion object {

        val BASE_URL = "http://api.worldweatheronline.com/premium/v1/"
        val API_KEY = "fb9c95117271455ebad71250191912"
        val RESPONSE_FORMAT = "json"

        fun create(): ApiInterface {

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(
                    RxJava2CallAdapterFactory.create()
                )
                .addConverterFactory(
                    GsonConverterFactory.create()
                )
                .baseUrl(BASE_URL)
                .build()

            return retrofit.create(ApiInterface::class.java)
        }

    }

    @GET("search.ashx")
    fun searchCity(
        @Query("key") key: String,
        @Query("q") q: String?,
        @Query("format") format: String
    ): Flowable<SearchResult>

    @GET("weather.ashx")
    fun weatherDetails(
        @Query("key") key: String,
        @Query("q") q: String?,
        @Query("format") format: String
    ): Flowable<WeatherDetail>

}