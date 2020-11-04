package com.example.soptseminar.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.soptseminar.model.User

class SignUpViewModel : ViewModel() {

    fun isValidate(name : String , id: String, password: String): Boolean {
        return !name.equals("") && !id.equals("") && !password.equals("")
    }
}