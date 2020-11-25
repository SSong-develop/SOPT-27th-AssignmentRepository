package com.example.soptseminar.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.soptseminar.data.remote.model.UserData
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

    val dummyDate = "2020.11.25"

    val dummyText = "앱개발자가 되고 싶습니다."

    val dummy = MakeDummy.makeDummyData()

    fun setUser(user : User){
        _user.value = user
    }

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