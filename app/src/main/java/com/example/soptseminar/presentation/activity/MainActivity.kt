package com.example.soptseminar.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.soptseminar.R
import com.example.soptseminar.databinding.ActivityMainBinding
import com.example.soptseminar.presentation.viewmodel.MainViewModel
import com.example.soptseminar.utils.Injection

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initViewModel()
    }

    private fun initViewModel(){
        viewModel = ViewModelProvider(this,Injection.provideMainViewModelFactory()).get(MainViewModel::class.java)
    }
}