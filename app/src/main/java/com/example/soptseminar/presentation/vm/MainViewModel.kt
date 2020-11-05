package com.example.soptseminar.presentation.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.soptseminar.data.sharedpref.SharedPref

class MainViewModel : ViewModel() {

    private val _name = MutableLiveData<String>()
    val name : LiveData<String>
        get() = _name

    private val _id = MutableLiveData<String>()
    val id : LiveData<String> // observing 할 객체
        get() = _id

    private val _password = MutableLiveData<String>()
    val password : LiveData<String>
        get() = _password

    val sharedPref = SharedPref
}