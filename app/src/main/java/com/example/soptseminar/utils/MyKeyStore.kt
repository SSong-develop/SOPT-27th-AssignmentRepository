package com.example.soptseminar.utils

object MyKeyStore {

    private const val LoginDataStoreName = "LoginDataStore"
    private const val autoLoginKey = "autoLoginKey"
    private const val SIGN_BASE_URL = "http://15.164.83.210:3000/"
    private const val DUMMYDATA_URL = "https://reqres.in/"

    fun provideLoginDataStoreName(): String {
        return LoginDataStoreName
    }

    fun provideAutoLoginKey(): String {
        return autoLoginKey
    }

    fun provideSignBaseUrl() : String{
        return SIGN_BASE_URL
    }

    fun provideDummyDataBaseUrl() : String{
        return DUMMYDATA_URL
    }

}