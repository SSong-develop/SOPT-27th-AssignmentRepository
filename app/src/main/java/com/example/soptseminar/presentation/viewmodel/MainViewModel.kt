package com.example.soptseminar.presentation.viewmodel

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.soptseminar.data.sharedpreference.SharedPref
import com.example.soptseminar.presentation.model.User

class MainViewModel : ViewModel() {
    val sharedPref = SharedPref

    private val _userId = MutableLiveData<String>()
    val userId : MutableLiveData<String>
        get() = _userId

    private val _userPassword = MutableLiveData<String>()
    val userPassword : MutableLiveData<String>
        get() = _userPassword


}