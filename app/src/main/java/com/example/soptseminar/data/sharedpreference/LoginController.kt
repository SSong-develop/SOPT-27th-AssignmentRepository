package com.example.soptseminar.data.sharedpreference

import android.content.SharedPreferences
import com.example.soptseminar.presentation.model.User
import com.example.soptseminar.utils.MyKeyStore

class LoginController(
    private val LoginDataStore : SharedPreferences
) : OnControlled{
    private val KEY = MyKeyStore.provideAutoLoginKey()

    override fun signIn(user: User): Boolean {
        return LoginDataStore.getString("id",null) == user.id &&
                LoginDataStore.getString("pw",null) == user.password
    }

    override fun signUp(user: User) {
        LoginDataStore.edit().putString("name",user.name).apply()
        LoginDataStore.edit().putString("id",user.id).apply()
        LoginDataStore.edit().putString("pw",user.password).apply()
    }

    override fun setAutoLoginKey() {
        LoginDataStore.edit().putBoolean(KEY,true).apply()
    }

    override fun isValidate(user: User): Boolean {
        return user.name.isNotBlank() && user.id.isNotBlank() && user.password.isNotBlank()
    }

    override fun getAutoLoginKey(): Boolean {
        return LoginDataStore.getBoolean(KEY,false)
    }

    override fun getUserName(): String {
        return LoginDataStore.getString("name",null).toString()
    }

}