package com.example.soptseminar.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.soptseminar.model.User

class MainViewModel : ViewModel() {
    // 데이터를 보관해주는
    // sharedPreference를 다르게 사용
    // LiveData를 이용해 회원가입에서 넘어올 떄 editText 갱신
    // Room
    // id와 password가 viewmodel에 담겨야 함 회전시 데이터가 사라짐
    // SharedPreference랑
    val SIGN_UP_CODE = 1001
/*    private val _user = MutableLiveData<User>()
    val user : MutableLiveData<User> // observe 할 녀석은 이녀석입니다.
        get() = _user*/
    private val _name = MutableLiveData<String>()
    val name : LiveData<String>
        get() = _name

    private val _id = MutableLiveData<String>()
    val id : LiveData<String> // observing 할 객체
        get() = _id

    private val _password = MutableLiveData<String>()
    val password : LiveData<String>
        get() = _password

    // id , password가 비어있는지 확인
    fun isValidate(id : String , password : String) : Boolean{
        return !id.equals("") && !password.equals("")
    }

    /**
     * Method when Main Came back
     */
    fun setUserName(name : String){
        _name.value = name
    }
    fun setUserId(id : String){
        _id.value = id
    }

    fun setUserPassword(password : String){
        _password.value = password
    }



}