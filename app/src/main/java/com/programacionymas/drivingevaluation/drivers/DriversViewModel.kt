package com.programacionymas.drivingevaluation.drivers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.programacionymas.drivingevaluation.data.DriverRepository
import com.programacionymas.drivingevaluation.domain.Driver
import com.programacionymas.drivingevaluation.ui.Screen
import com.programacionymas.drivingevaluation.util.Event

class DriversViewModel(private val driversRepository: DriverRepository) : ViewModel() {

    private val _navigateTo = MutableLiveData<Event<Screen>>()
    val navigateTo: LiveData<Event<Screen>>
        get() = _navigateTo

    val driversData = driversRepository.driversLiveData

    fun evaluate(driver: Driver) {
        _navigateTo.value = Event(Screen.Test)
    }

    init {
        // driversRepository.fetchDrivers()
    }

}

class DriversViewModelFactory : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DriversViewModel::class.java)) {
            return DriversViewModel(DriverRepository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}