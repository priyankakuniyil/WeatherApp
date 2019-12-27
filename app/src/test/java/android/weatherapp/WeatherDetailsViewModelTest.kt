package android.weatherapp

import android.weatherapp.viewmodel.WeatherDetailsViewModel
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.After
import org.junit.Before

import org.junit.Rule
import org.junit.Test
import retrofit2.Response
import java.lang.Exception
import io.reactivex.schedulers.Schedulers
import io.reactivex.android.plugins.RxAndroidPlugins



class WeatherDetailsViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private var weatherDetailViewModel: WeatherDetailsViewModel? = null
    private var query: String? = null

    @Before
    fun setUp() {
        weatherDetailViewModel = WeatherDetailsViewModel()

        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler -> Schedulers.trampoline() }
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