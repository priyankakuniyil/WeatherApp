package android.weatherapp

import android.weatherapp.util.isNetworkAvailable
import org.junit.Assert
import org.junit.Test

class UtilTest {
    @Test
    fun testisNetworkAvailable()
    {
        Assert.assertEquals(isNetworkAvailable(null),false)
    }

}