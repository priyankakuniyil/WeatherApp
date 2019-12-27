package android.weatherapp.viewmodel

import SearchResult
import android.weatherapp.db.retrofit.ProjectRepository
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SearchCityViewModel : ViewModel() {

    var cities = MutableLiveData<SearchResult>()

    fun getCityNames(query: String?): MutableLiveData<SearchResult> {
        cities = ProjectRepository().getCityNames(query)
        return cities
    }

}