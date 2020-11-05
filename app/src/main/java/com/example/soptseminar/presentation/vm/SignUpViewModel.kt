package com.example.soptseminar.presentation.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.soptseminar.data.model.User

class SignUpViewModel : ViewModel() {

    private val _user = MutableLiveData<User>()
    val user : MutableLiveData<User>
        get() = _user

}