package com.example.soptseminar.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModelProvider
import com.example.soptseminar.data.remote.api.RetrofitBuilder
import com.example.soptseminar.data.remote.service.RetrofitService
import com.example.soptseminar.presentation.factory.MainViewModelFactory
import com.example.soptseminar.repository.MainRepository

object Injection {

    fun provideLoginDataStore(context: Context): SharedPreferences {
        return context.getSharedPreferences(
            MyKeyStore.provideLoginDataStoreName(),
            Context.MODE_PRIVATE
        )
    }

    private fun provideRetrofitService(): RetrofitService {
        return RetrofitBuilder.retrofitService
    }

    private fun provideDummyRetrofitService() : RetrofitService{
        return RetrofitBuilder.dummyDataRetrofitService
    }

    private fun provideMainRepository(): MainRepository {
        return MainRepository(provideRetrofitService(), provideDummyRetrofitService())
    }

    fun provideMainViewModelFactory(): ViewModelProvider.Factory {
        return MainViewModelFactory(provideMainRepository())
    }

}