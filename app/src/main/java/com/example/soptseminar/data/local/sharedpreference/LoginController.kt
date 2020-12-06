package com.example.soptseminar.data.local.sharedpreference

import android.content.SharedPreferences
import com.example.soptseminar.data.remote.model.UserData
import com.example.soptseminar.utils.MyKeyStore

class LoginController(
    private val LoginDataStore: SharedPreferences
) : OnControlled {
    private val KEY = MyKeyStore.provideAutoLoginKey()

    override fun setAutoLogin() {
        LoginDataStore.edit().putBoolean(KEY, true).apply()
    }

    override fun saveUserData(userData: UserData) {
        LoginDataStore.edit()
            .putString("userName",userData.userName)
            .putString("userEmail",userData.email)
            .putString("userPassword",userData.password)
            .apply()
    }

    override fun fetchUserData(): UserData {
        return UserData(
            LoginDataStore.getString("userEmail",null).toString(),
            LoginDataStore.getString("userPassword",null).toString(),
            LoginDataStore.getString("userName",null).toString()
        )
    }

    override fun getAutoLogin(): Boolean {
        return LoginDataStore.getBoolean(KEY, false)
    }

}