package android.weatherapp

import java.text.SimpleDateFormat
import java.util.*

class Util {

    val dateFormat = "yyyy-MM-dd HH:mm:ss"


    fun currentDate(): String {
        val dateFormat = SimpleDateFormat(dateFormat, Locale.getDefault())
        val date = Date()
        return dateFormat.format(date)
    }

}