package android.weatherapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.weatherapp.R
import android.weatherapp.model.RecentCity
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recent_search.view.*

class RecentSearchAdapter(val items: List<RecentCity>, val context: Context) :  RecyclerView.Adapter<ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.city_Name.text = items.get(position).cityName
        holder.city_Weather.text = items.get(position).cityWeather

        Picasso.get()
            .load(items.get(position).cityWeatherImage)
            .placeholder(R.drawable.ic_cloud_queue_black_24dp)
            .error(R.drawable.ic_cloud_queue_black_24dp)
            .into(holder.weather_Image)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.recent_search,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val weather_Image = view.img_weather
    val city_Name = view.txt_place
    val city_Weather = view.txt_weather
}