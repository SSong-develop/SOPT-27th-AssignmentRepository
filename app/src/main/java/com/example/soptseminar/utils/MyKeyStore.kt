package com.example.soptseminar.utils

object MyKeyStore {
    private const val LoginDataStoreName = "LoginDataStore"
    private const val autoLoginKey = "autoLoginKey"
    private const val BASE_URL = "http://15.164.83.210:3000/"

    fun provideLoginDataStoreName(): String {
        return LoginDataStoreName
    }

    fun provideAutoLoginKey(): String {
        return autoLoginKey
    }

    fun provideBaseUrl() : String{
        return BASE_URL
    }

}