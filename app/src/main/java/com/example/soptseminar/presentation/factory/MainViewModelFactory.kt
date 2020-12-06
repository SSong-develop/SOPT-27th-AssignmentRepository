package com.example.soptseminar.presentation.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.soptseminar.presentation.viewmodel.MainViewModel
import com.example.soptseminar.repository.MainRepository

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(private val repository: MainRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        require(modelClass.isAssignableFrom(MainViewModel::class.java)){"Unknown class name"}
        return MainViewModel(repository) as T
    }
}