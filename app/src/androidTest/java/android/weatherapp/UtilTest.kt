package android.weatherapp

import org.junit.Assert
import org.junit.Test

class UtilTest {
    @Test
    fun testisNetworkAvailable()
    {
        Assert.assertEquals(isNetworkAvailable(null),false)
    }

}