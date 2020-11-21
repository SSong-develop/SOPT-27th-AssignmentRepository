package com.example.soptseminar.utils

object MyKeyStore {
    private const val LoginDataStoreName = "LoginDataStore"
    private const val autoLoginKey = "autoLoginKey"

    fun provideLoginDataStoreName(): String {
        return LoginDataStoreName
    }

    fun provideAutoLoginKey(): String {
        return autoLoginKey
    }

}