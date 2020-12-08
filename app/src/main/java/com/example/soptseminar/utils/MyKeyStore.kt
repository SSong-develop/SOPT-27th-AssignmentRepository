package com.example.soptseminar.utils

object MyKeyStore {

    private const val LoginDataStoreName = "LoginDataStore"
    private const val autoLoginKey = "autoLoginKey"
    private const val SIGN_BASE_URL = "http://15.164.83.210:3000/"
    private const val DUMMYDATA_BASE_URL = "https://reqres.in/"
    private const val KAKAO_SEARCH_BASE_URL = "https://dapi.kakao.com"
    private const val KAKAO_WEB_SEARCH_APIKEY = "7411840c70828638540dc83a89c1c33b"

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
        return DUMMYDATA_BASE_URL
    }

    fun provideKakaoBaseUrl() : String{
        return KAKAO_SEARCH_BASE_URL
    }

    fun provideKakaoWebSearchApiKey() : String{
        return KAKAO_WEB_SEARCH_APIKEY
    }


}