package com.example.soptseminar.presentation.activity

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Base64
import android.util.Base64.NO_WRAP
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.soptseminar.R
import com.example.soptseminar.databinding.ActivityMainBinding
import com.example.soptseminar.presentation.viewmodel.MainViewModel
import com.example.soptseminar.utils.Injection
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initViewModel()
    }

    private fun initViewModel(){
        viewModel = ViewModelProvider(this, Injection.provideMainViewModelFactory()).get(
            MainViewModel::class.java
        )
    }

}