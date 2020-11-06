package com.example.soptseminar.utils

class Validator {
    companion object{
        fun isValidate(vararg text : String): Boolean {
            if(text.equals("")){
                return false
            }
            return true
        }
    }
}