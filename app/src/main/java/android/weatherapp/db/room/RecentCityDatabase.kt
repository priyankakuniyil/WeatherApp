package android.weatherapp.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(RecentCity::class), version = 1)
abstract class RecentCityDatabase : RoomDatabase() {
    abstract fun recentCityDao(): RecentCityDao

    companion object {
        private var INSTANCE: RecentCityDatabase? = null
        fun getDatabase(context: Context): RecentCityDatabase? {
            if (INSTANCE == null) {
                synchronized(RecentCityDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        RecentCityDatabase::class.java, "WeatherApp.db"
                    ).build()
                }
            }
            return INSTANCE

        }
    }
}