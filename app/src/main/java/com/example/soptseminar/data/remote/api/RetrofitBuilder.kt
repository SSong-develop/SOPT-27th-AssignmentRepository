package com.example.soptseminar.data.remote.api

import com.example.soptseminar.data.remote.service.RetrofitService
import com.example.soptseminar.utils.MyKeyStore
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    private fun getInstance(BaseUrl : String) : Retrofit{
        return Retrofit.Builder()
            .baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val retrofitService : RetrofitService = getInstance(MyKeyStore.provideSignBaseUrl()).create(RetrofitService::class.java)

    val dummyDataRetrofitService : RetrofitService = getInstance(MyKeyStore.provideDummyDataBaseUrl()).create(RetrofitService::class.java)

    val kakaoSearchRetrofitService : RetrofitService = getInstance(MyKeyStore.provideKakaoBaseUrl()).create(RetrofitService::class.java)
}