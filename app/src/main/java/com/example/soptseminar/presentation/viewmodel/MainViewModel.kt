package com.example.soptseminar.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.soptseminar.data.remote.model.UserData
import com.example.soptseminar.data.remote.model.UserInfo
import com.example.soptseminar.model.SignInUser
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

    private val _userData = MutableLiveData<UserData>()
    val userData : MutableLiveData<UserData>
        get() = _userData

    val dummy = MakeDummy.makeDummyData()

    // TODO : Rename Function
    fun setUser(user : User){
        _user.value = user
    }

    // TODO : Rename Function
    fun setUserData(userData: UserData){
        _userData.value = userData
    }

    fun signIn(signInUser: SignInUser) = viewModelScope.launch{
        try{
            _userData.value = repository.signIn(signInUser).userData
        } catch (e : Exception){
            e.printStackTrace()
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