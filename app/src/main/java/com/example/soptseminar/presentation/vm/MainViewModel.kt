package com.example.soptseminar.presentation.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.soptseminar.data.sharedpref.SharedPref

class MainViewModel : ViewModel() {
    val sharedPref = SharedPref
}