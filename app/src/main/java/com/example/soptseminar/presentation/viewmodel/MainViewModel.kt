package com.example.soptseminar.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.soptseminar.data.remote.model.DummyData
import com.example.soptseminar.data.remote.model.KakaoSearchData
import com.example.soptseminar.data.remote.model.UserData
import com.example.soptseminar.model.SignInUser
import com.example.soptseminar.model.User
import com.example.soptseminar.repository.MainRepository
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: MainRepository
) : ViewModel() {

    private val _keyword = MutableLiveData<String>()
    val keyword : MutableLiveData<String>
        get() = _keyword

    private val _user = MutableLiveData<User>()
    val user : MutableLiveData<User>
        get() = _user

    private val _userData = MutableLiveData<UserData>()
    val userData : MutableLiveData<UserData>
        get() = _userData

    private val _dummyData = MutableLiveData<DummyData>()
    val dummyData : MutableLiveData<DummyData>
        get() = _dummyData

    private val _searchData = MutableLiveData<KakaoSearchData>()
    val searchData : MutableLiveData<KakaoSearchData>
        get() = _searchData


    fun setUser(user : User){
        _user.value = user
    }

    fun remoteDummy() = viewModelScope.launch {
        _dummyData.value = repository.fetchDummy()
    }

    fun setUserData(userData: UserData){
        _userData.value = userData
    }

    fun setKeyword(keyword : String) {
        _keyword.value = keyword
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

    fun search() = viewModelScope.launch {
        _searchData.value = repository.searchKeyword(_keyword.value.toString())
    }

}