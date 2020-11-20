package com.example.soptseminar.utils

import android.content.Context
import android.content.SharedPreferences

object Injection {

    fun provideLoginDataStore(context: Context): SharedPreferences {
        return context.getSharedPreferences(
            MyKeyStore.provideLoginDataStoreName(),
            Context.MODE_PRIVATE
        )
    }

}