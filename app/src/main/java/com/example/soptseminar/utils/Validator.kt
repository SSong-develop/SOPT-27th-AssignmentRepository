package com.example.soptseminar.utils

// 다시 한번 생각해보자구용~~~
// isNullOrEmpty가 boolean값이 반환되는데 여기서 또 boolean을 반환한다는게 이상하다.
// 요거 생각을 해봅시당~~~
class Validator {
    companion object{
        fun isValidate(vararg text : String): Boolean {
            if(text.isNullOrEmpty()){
                return true
            }
            return false
        }
    }
}