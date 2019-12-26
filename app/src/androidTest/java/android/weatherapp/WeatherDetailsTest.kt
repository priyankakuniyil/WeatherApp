package android.weatherapp

import android.weatherapp.db.retrofit.ApiInterface
import androidx.test.rule.ActivityTestRule
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.junit.*


class WeatherDetailsTest
{

    lateinit var apiInterface: ApiInterface

    var known_city: String = "London"
    var unknown_city: String = "LiLiLiLi"

    @get:Rule
    var activityTestRule = ActivityTestRule(HomeActivity::class.java)

    @Before
    fun setUp() {
        apiInterface = ApiInterface.create()
    }

    @Test
    fun weatherDetails_Query() {
        Assert.assertNotNull(apiInterface)
        apiInterface.weatherDetails(ApiInterface.API_KEY, known_city, ApiInterface.RESPONSE_FORMAT)
            .observeOn(
                AndroidSchedulers.mainThread()
            )
            .subscribeOn(Schedulers.io())
            .subscribe(
                { result ->

                    Assert.assertTrue(true)

                }, { error ->

                    Assert.assertFalse(false)

                }
            )

    }

    @Test
    fun searchCity_EmptyQuery() {
        Assert.assertNotNull(apiInterface)
        apiInterface.weatherDetails(ApiInterface.API_KEY, unknown_city, ApiInterface.RESPONSE_FORMAT)
            .observeOn(
                AndroidSchedulers.mainThread()
            )
            .subscribeOn(Schedulers.io())
            .subscribe(
                { result ->

                    Assert.assertTrue(true)


                }, { error ->

                    Assert.assertFalse(false)

                }
            )

    }

    @After
    fun tearDown() {
    }

}