package com.example.soptseminar.presentation.activity

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.soptseminar.R
import com.example.soptseminar.presentation.model.ProfileData
import com.example.soptseminar.presentation.vm.MainViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun detailPage(data: ProfileData){
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("title",data.title)
        intent.putExtra("subtitle",data.subTitle)
        intent.putExtra("date","2020-10-23")
        intent.putExtra("detail","살아있는 누군가입니다.")
        startActivity(intent)
    }
}