package android.weatherapp

import java.text.SimpleDateFormat
import java.util.*
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity


class Util {

    val dateFormat = "yyyy-MM-dd HH:mm:ss"


    fun currentDate(): String {
        val dateFormat = SimpleDateFormat(dateFormat, Locale.getDefault())
        val date = Date()
        return dateFormat.format(date)
    }

    fun isNetworkAvailable(activity: AppCompatActivity): Boolean {
        val connectivityManager =
            activity.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

}