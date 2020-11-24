package com.example.soptseminar.data.remote.api

import com.example.soptseminar.data.remote.service.RetrofitService
import com.example.soptseminar.utils.MyKeyStore
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    private fun getInstance() : Retrofit{
        return Retrofit.Builder()
            .baseUrl(MyKeyStore.provideBaseUrl())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val retrofitService : RetrofitService = getInstance().create(RetrofitService::class.java)
}