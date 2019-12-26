package android.weatherapp

import android.weatherapp.db.retrofit.ApiInterface
import androidx.test.rule.ActivityTestRule
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class HomeActivityTest {

    lateinit var apiInterface: ApiInterface

    var query: String = "London"
    var empty_query: String = ""

    @get:Rule
    var activityTestRule = ActivityTestRule(HomeActivity::class.java)

    @Before
    fun setUp() {
        apiInterface = ApiInterface.create()
    }

    @Test
    fun searchCity_Query() {
        assertNotNull(apiInterface)
        apiInterface.searchCity(ApiInterface.API_KEY, query, ApiInterface.RESPONSE_FORMAT)
            .observeOn(
                AndroidSchedulers.mainThread()
            )
            .subscribeOn(Schedulers.io())
            .subscribe(
                { result ->

                    assertTrue(true)

                }, { error ->

                    assertFalse(false)

                }
            )

    }

    @Test
    fun searchCity_EmptyQuery() {
        assertNotNull(apiInterface)
        apiInterface.searchCity(ApiInterface.API_KEY, empty_query, ApiInterface.RESPONSE_FORMAT)
            .observeOn(
                AndroidSchedulers.mainThread()
            )
            .subscribeOn(Schedulers.io())
            .subscribe(
                { result ->

                    assertTrue(true)


                }, { error ->

                    assertFalse(false)

                }
            )

    }

    @After
    fun tearDown() {
    }

}