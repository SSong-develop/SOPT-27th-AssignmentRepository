package com.example.soptseminar.data.local.sharedpreference

import android.content.SharedPreferences
import com.example.soptseminar.utils.MyKeyStore

class LoginController(
    private val LoginDataStore: SharedPreferences
) : OnControlled {
    private val KEY = MyKeyStore.provideAutoLoginKey()

    override fun setAutoLoginKey() {
        LoginDataStore.edit().putBoolean(KEY, true).apply()
    }

    override fun getAutoLoginKey(): Boolean {
        return LoginDataStore.getBoolean(KEY, false)
    }

    override fun getUserName(): String {
        return LoginDataStore.getString("name", null).toString()
    }

}