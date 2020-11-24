package com.example.soptseminar.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.soptseminar.data.remote.model.UserInfo
import com.example.soptseminar.model.User
import com.example.soptseminar.repository.MainRepository
import com.example.soptseminar.utils.MakeDummy
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: MainRepository
) : ViewModel() {

    private val _user = MutableLiveData<User>()
    val user : MutableLiveData<User>
        get() = _user

    private val _userInfo = MutableLiveData<UserInfo>()
    val userInfo : MutableLiveData<UserInfo>
        get() = _userInfo

    val dummy = MakeDummy.makeDummyData()

    fun setUser(user : User){
        _user.value = user
    }

    fun signIn() = viewModelScope.launch{
        _user.value?.let {
            try {
                repository.signIn(it)
            } catch (e : NullPointerException){
                e.printStackTrace()
            } catch (e : Exception){
                e.printStackTrace()
            }
        }
    }

    fun signUp() = viewModelScope.launch{
        _user.value?.let {
            try{
                repository.signUp(it)
            } catch (e : NullPointerException){
                e.printStackTrace()
            } catch (e : Exception){
                e.printStackTrace()
            }
        }
    }

}