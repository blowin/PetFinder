package com.lefarmico.petfinder.unitConverterApp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lefarmico.petfinder.Repository

class ViewModelFactory(private val repository: Repository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(TemperatureViewModel::class.java) -> {
                TemperatureViewModel(repository) as T
            }
            modelClass.isAssignableFrom(DistancesViewModel::class.java) -> {
                DistancesViewModel(repository) as T
            }
            else -> {
                throw ClassCastException("Impossible to cast $modelClass")
            }
        }
    }
}
