package android.weatherapp.viewmodel

import android.weatherapp.db.sqlite.DatabaseHelper
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.After
import org.junit.Before

import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import retrofit2.Response
import java.lang.Exception

class WeatherDetailsViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private inline fun <reified T> mock() = Mockito.mock(T::class.java)

    private var weatherDetailViewModel: WeatherDetailsViewModel? = null
    private var query: String? = null

    @Before
    fun setUp() {
        weatherDetailViewModel = WeatherDetailsViewModel()
    }

    @After
    fun tearDown() {
        weatherDetailViewModel = null
    }

    @Test
    fun testweatherDetailsNullValue() {

        weatherDetailViewModel?.weatherDetails(query)?.observeForever {
            assert((it as Response<*>).body() is Exception)
        }

    }

    @Test
    fun testweatherDetailsValue() {
        query = "London"
        weatherDetailViewModel?.weatherDetails(query)?.observeForever {
            assert((it as Response<*>).body() is Exception)
        }

    }

}