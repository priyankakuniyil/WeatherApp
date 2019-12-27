package android.weatherapp

import android.weatherapp.viewmodel.SearchCityViewModel
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response
import java.lang.Exception

class SearchCityViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private var searchCityViewModel: SearchCityViewModel? = null
    private var query: String? = null

    @Before
    fun setUp() {
        searchCityViewModel = SearchCityViewModel()
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler -> Schedulers.trampoline() }
    }

    @After
    fun tearDown() {
        searchCityViewModel = null
    }

    @Test
    fun testgetCityNamesNullValue() {

        searchCityViewModel?.getCityNames(query)?.observeForever {
            assert((it as Response<*>).body() is Exception)
        }

    }

    @Test
    fun testgetCityNamesValue() {
        query = "London"
        searchCityViewModel?.getCityNames(query)?.observeForever {
            assert((it as Response<*>).body() is Exception)
        }

    }

}