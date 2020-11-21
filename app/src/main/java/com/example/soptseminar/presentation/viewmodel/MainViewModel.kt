package com.example.soptseminar.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.soptseminar.utils.MakeDummy

class MainViewModel : ViewModel() {
    private val _userId = MutableLiveData<String>()
    val userId: MutableLiveData<String>
        get() = _userId

    private val _userPassword = MutableLiveData<String>()
    val userPassword: MutableLiveData<String>
        get() = _userPassword

    val dummy = MakeDummy.makeDummyData()

}