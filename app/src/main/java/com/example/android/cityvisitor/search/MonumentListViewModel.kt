package com.example.android.cityvisitor.search

import android.location.Location
import android.util.Log
import androidx.lifecycle.*
import com.example.android.cityvisitor.network.CityVisitorApi
import com.example.android.cityvisitor.network.Monuments
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.IOException


class MonumentListViewModel: ViewModel() {

    private val repository = MonumentsRepository(CityVisitorApi.RETROFIT_SERVICE)

    private var filter = FilterHolder()

    private var currentJob: Job? = null

    private val _monumentList = MutableLiveData<List<Monuments>>()
    private val _regionList = MutableLiveData<List<String>>()
    private val _showNeedLocation = MutableLiveData<Boolean>()

    // The external LiveData interface to the property is immutable, so only this class can modify
    val monumentList: LiveData< List<Monuments>>
        get() = _monumentList

    val regionList: LiveData<List<String>>
        get() = _regionList

    val showNeedLocation: LiveData<Boolean>
        get() = _showNeedLocation

    init {
        // process the initial filter
        onQueryChanged()

        viewModelScope.launch {
            delay(5_000)
            _showNeedLocation.value = !repository.isFullyInitialized
        }
    }



    fun addMonumentToFavourite(monument: Monuments){
        viewModelScope.launch {
            repository.addToFavourite(monument)
        }
    }



    private fun onQueryChanged() {
        currentJob?.cancel() // if a previous query is running cancel it before starting another
        currentJob = viewModelScope.launch {
            try {
                // this will run on a thread managed by Retrofit
                _monumentList.value = repository.getMonumentsForFilter(filter.currentValue)
                repository.getFilters().let {
                    // only update the filters list if it's changed since the last time
                    if (it != _regionList.value) {
                        _regionList.value = it
                    }
                }
            } catch (e: IOException) {
                _monumentList.value = listOf()
            }
        }
    }

    fun onLocationUpdated(location: Location) {
        viewModelScope.launch {
            repository.onLocationChanged(location)
            onQueryChanged()
        }
    }

    fun onFilterChanged(filter: String, isChecked: Boolean) {
        if (this.filter.update(filter, isChecked)) {
            onQueryChanged()
        }
    }

    private class FilterHolder {
        var currentValue: String? = null
            private set

        fun update(changedFilter: String, isChecked: Boolean): Boolean {
            if (isChecked) {
                currentValue = changedFilter
                return true
            } else if (currentValue == changedFilter) {
                currentValue = null
                return true
            }
            return false
        }
    }
}

