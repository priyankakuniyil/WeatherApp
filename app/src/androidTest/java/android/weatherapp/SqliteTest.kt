package android.weatherapp

import Current_condition
import Data
import Request
import WeatherDesc
import WeatherDetail
import WeatherIconUrl
import android.weatherapp.db.sqlite.DatabaseHelper
import android.weatherapp.viewmodel.SqliteViewModel
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.After
import org.junit.Assert.*
import org.junit.runner.RunWith
import org.junit.Before
import org.junit.Test

@RunWith(AndroidJUnit4::class)
class SqliteTest {

    private var weatherDetail: WeatherDetail? = null

    private var dbHelper: DatabaseHelper? = null

    private var sqliteViewModel: SqliteViewModel? = null

    @Before
    fun setUp() {
        dbHelper = DatabaseHelper(InstrumentationRegistry.getInstrumentation().getTargetContext())
        dbHelper!!.openWritableDB()
        sqliteViewModel = SqliteViewModel()
    }

    @After
    fun finish() {
        dbHelper!!.closeDB()
    }

    @Test
    fun testaddCityWeather() {
        sqliteViewModel!!.deleteEntries(dbHelper)
        weatherDetail = dummydata()
        val result = sqliteViewModel!!.addCityWeather(dbHelper, weatherDetail!!)
        assertEquals(1, result)
    }

    @Test
    fun testgetRecentSearches() {
        sqliteViewModel!!.deleteEntries(dbHelper)
        sqliteViewModel!!.addCityWeather(dbHelper, dummydata())
        val result = sqliteViewModel!!.getRecentSearches(dbHelper)
        assertEquals(1, result.size)
    }

    fun dummydata(): WeatherDetail {
        val request = Request("", "")
        val url = WeatherIconUrl("http://cdn.worldweatheronline.net/images/wsymbols01_png_64/wsymbol_0004_black_low_cloud.png")
        val desc = WeatherDesc("")
        val currentcondition = Current_condition(0, arrayListOf(url), arrayListOf(desc), 0)
        val data = Data(arrayListOf(request), arrayListOf(currentcondition))
        return WeatherDetail(data)
    }
}