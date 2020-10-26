package com.example.soptseminar.ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.soptseminar.R
import com.example.soptseminar.databinding.ActivitySignUpBinding
import com.example.soptseminar.model.User
import com.example.soptseminar.viewmodel.SignUpViewModel
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    private lateinit var autoLogin : SharedPreferences
    private lateinit var binding : ActivitySignUpBinding
    private lateinit var viewModel : SignUpViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_sign_up)
        viewModel = ViewModelProvider(this).get(SignUpViewModel::class.java)
        binding.signupviewmodel = viewModel
        binding.signupactivity = this

        init()
    }

    fun init(){
        autoLogin = getSharedPreferences("autoLogin1", MODE_PRIVATE)
    }

    fun goMain(view : View){
        if(viewModel.isValidate(binding.signUpNameEdtxt.text.toString(),
                binding.signUpIdEdt.text.toString(),
                binding.signUpPassEdt.text.toString())){
            regist()
            sendIntent()
        }
        else {
            Toast.makeText(this,"공백없이 전부 적어주세요",Toast.LENGTH_SHORT).show()
        }
    }

    fun regist(){
        autoLogin.edit().putString("name",binding.signUpNameEdtxt.text.toString()).commit()
        autoLogin.edit().putString("id",binding.signUpIdEdt.text.toString()).commit()
        autoLogin.edit().putString("password",binding.signUpPassEdt.text.toString()).commit()
    }

    fun sendIntent(){
        val newIntent = Intent()
        newIntent.putExtra("name",binding.signUpNameEdtxt.text.toString())
        newIntent.putExtra("id",binding.signUpIdEdt.text.toString())
        newIntent.putExtra("password",binding.signUpPassEdt.text.toString())
        setResult(RESULT_OK,newIntent)
        finish()
    }
}