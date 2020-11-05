package com.example.soptseminar.data.sharedpref

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.example.soptseminar.R

object SharedPref {
    private val prefName = R.string.sharedpreference_name.toString()

    @Volatile
    private var INSTANCE : SharedPreferences? = null

    private fun getInstance(context : Context) : SharedPreferences? {
        synchronized(this){
            var instance = INSTANCE
            if(instance == null){
                instance = context.getSharedPreferences(prefName, MODE_PRIVATE)
                INSTANCE = instance
            }
            return instance
        }
    }

    fun getBooleanValue(context : Context , KEY : String) : Boolean? {
        return getInstance(context)?.getBoolean(KEY,false)
    }

    fun putBooleanValue(context : Context, KEY : String , value : Boolean){
        val editor = getInstance(context)?.edit()
        value.let {
            editor?.putBoolean(KEY,it)
        }
        editor?.apply()
    }

    fun getStringValue(context : Context , KEY: String): String? {
        return getInstance(context)?.getString(KEY,null)
    }

    fun putStirngValue(context : Context, KEY : String , value : String){
        val editor = getInstance(context)?.edit()
        value.let {
            editor?.putString(KEY,it)
        }
        editor?.apply()
    }

}