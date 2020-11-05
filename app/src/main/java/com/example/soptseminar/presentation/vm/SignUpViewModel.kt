package com.example.soptseminar.presentation.vm

import androidx.lifecycle.ViewModel

class SignUpViewModel : ViewModel() {

    fun isValidate(name : String , id: String, password: String): Boolean {
        return !name.equals("") && !id.equals("") && !password.equals("")
    }
}