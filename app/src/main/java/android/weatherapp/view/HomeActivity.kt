package android.weatherapp.view

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.weatherapp.R
import android.weatherapp.adapter.RecentSearchAdapter
import android.weatherapp.db.sqlite.DatabaseHelper
import android.weatherapp.util.isNetworkAvailable
import android.weatherapp.viewmodel.SearchCityViewModel
import android.widget.AutoCompleteTextView
import android.widget.SimpleAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.home_activity.*

class HomeActivity : AppCompatActivity() {

    lateinit var et_search: AutoCompleteTextView
    lateinit var rv_recent_list: RecyclerView

    var cityNames: ArrayList<String> = ArrayList()
    var hm_cityNames: ArrayList<HashMap<String, String>> = ArrayList()

    lateinit var searchCityViewModel: SearchCityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)

        searchCityViewModel =
            ViewModelProviders.of(this@HomeActivity).get(SearchCityViewModel::class.java)

        rv_recent_list = findViewById(R.id.rv_recent_list)
        et_search = findViewById(R.id.et_search)
        et_search.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

                try {

                    if (!isNetworkAvailable(this@HomeActivity)) {
                        Toast.makeText(
                            this@HomeActivity,
                            getString(R.string.network_connection),
                            Toast.LENGTH_SHORT
                        ).show()

                    } else {

                        observerCitySearch()

                    }


                } catch (e: Exception) {

                }

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

        })

        et_search.setOnItemClickListener { _, _, position, _ ->

            try {

                et_search.setText(cityNames[position].split(",")[0])

                val intent = Intent(this, WeatherDetails::class.java)
                intent.putExtra("city_name", cityNames[position].split(",")[0])
                startActivity(intent)

            } catch (e: Exception) {

            }

        }

        setList()

    }

    override fun onResume() {
        super.onResume()

        clearSelection()
        setList()

    }

    fun setList() {
        if (DatabaseHelper(this).viewRecentSearch().isNotEmpty()) {
            ly_no_search.visibility = View.GONE

            rv_recent_list.layoutManager = LinearLayoutManager(this)
            rv_recent_list.adapter =
                RecentSearchAdapter(DatabaseHelper(this).viewRecentSearch(), this)
        } else {
            ly_no_search.visibility = View.VISIBLE
        }
    }

    fun clearSelection() {

        et_search.clearFocus()
        et_search.dismissDropDown()
        et_search.setText("")

    }

    fun observerCitySearch() {

        searchCityViewModel.getCityNames(et_search.text.toString())
            .observe(this@HomeActivity, Observer {

                if (et_search.text.toString().length > 0) {

                    Log.e("Result", Gson().toJson(it))
                    cityNames.clear()
                    hm_cityNames.clear()

                    for (i in 0 until it.search_api.result.size) {
                        cityNames.add(it.search_api.result[i].areaName[0].value + "," + it.search_api.result[i].country[0].value)

                        Log.e(
                            "Loop $i",
                            it.search_api.result[i].areaName[0].value + " " + it.search_api.result[i].country[0].value
                        )

                        val hashMap: HashMap<String, String> = HashMap()
                        hashMap.put("region", it.search_api.result[i].areaName[0].value)
                        hashMap.put("country", it.search_api.result[i].country[0].value)
                        hm_cityNames.add(hashMap)
                    }

                    val from = arrayOf("region", "country")
                    val to = intArrayOf(
                        R.id.txt_region,
                        R.id.txt_country
                    )
                    val adapter = SimpleAdapter(
                        this@HomeActivity,
                        hm_cityNames,
                        R.layout.auto_complete_list_item,
                        from,
                        to
                    )

                    et_search.setAdapter(adapter)
                    et_search.showDropDown()

                }

            })

    }

}
