package com.example.android.cityvisitor.add

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.viewModelScope
import com.example.android.cityvisitor.network.CityVisitorApi
import com.example.android.cityvisitor.network.Monuments
import com.example.android.cityvisitor.search.MonumentsRepository
import kotlinx.coroutines.launch

class AddMonumentViewModel : ViewModel() {

    val name = MutableLiveData<String>()
    val description = MutableLiveData<String>()
    val category = MutableLiveData<String>()
    val url = MutableLiveData<String>()
    val rate = MutableLiveData<String>()
    val lat = MutableLiveData<String>()
    val lng = MutableLiveData<String>()

    private val repository = MonumentsRepository(CityVisitorApi.RETROFIT_SERVICE)

    var monumentCreator = MonumentCreator()

    fun addMonument() {
        viewModelScope.launch {
            name.value?.let { monumentCreator.name = it }
            description.value?.let { monumentCreator.description = it }
            category.value?.let { monumentCreator.category = it }
            url.value?.let { monumentCreator.url = it }
            rate.value?.let { monumentCreator.rate = it.toFloat() }
            lat.value?.let { monumentCreator.lat = it.toDouble() }
            lng.value?.let { monumentCreator.lng = it.toDouble() }
            val monuments = monumentCreator.createMonument()

            repository.addMonument(monuments)
        }
    }



    /**
     * Request a toast by setting this value to true.
     *
     * This is private because we don't want to expose setting this value to the Fragment.
     */
    private var _showSnackbarEvent = MutableLiveData<Boolean?>()

    /**
     * If this is true, immediately `show()` a toast and call `doneShowingSnackbar()`.
     */
    val showSnackBarEvent: LiveData<Boolean?>
        get() = _showSnackbarEvent

    /**
     * Call this immediately after calling `show()` on a toast.
     *
     * It will clear the toast request, so if the user rotates their phone it won't show a duplicate
     * toast.
     */
    fun doneShowingSnackbar() {
        _showSnackbarEvent.value = null
    }

    fun onSubmitApplication() {
        _showSnackbarEvent.value = true

    }
}
