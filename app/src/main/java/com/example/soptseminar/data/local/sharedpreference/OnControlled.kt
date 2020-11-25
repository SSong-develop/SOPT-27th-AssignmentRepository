package com.example.soptseminar.data.local.sharedpreference

import com.example.soptseminar.data.remote.model.UserData

interface OnControlled {

    fun setAutoLogin()

    fun saveUserData(userData: UserData)

    fun fetchUserData() : UserData

    fun getAutoLogin(): Boolean

}