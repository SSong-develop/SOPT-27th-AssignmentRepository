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
import com.example.soptseminar.presentation.vm.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var autoLogin: SharedPreferences
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /*binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java) // change ktx
        binding.mainviewmodel = viewModel
        binding.activity = this*/

       /* init()*/
    }

   /* fun init() {
        autoLogin = getSharedPreferences("autoLogin1", MODE_PRIVATE)
        auto(autoLogin)
    }

    private fun auto(sharedPreferences: SharedPreferences) {
        if(sharedPreferences.getBoolean("autoKey",false) == true){
            Toast.makeText(this,"자동로그인",Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }

    fun goSign(view: View) {
        startActivityForResult(Intent(this, SignUpActivity::class.java), viewModel.SIGN_UP_CODE)
    }

    fun goLogin(view: View) {
        if (viewModel.isValidate(
                binding.mainIdEdt.text.toString(),
                binding.mainPassEdt.text.toString()
            )
        ) {
            isRegist()
        }
        else {
            Toast.makeText(this,"회원정보가 틀렸습니다.",Toast.LENGTH_SHORT).show()
        }
    }

    fun isRegist() {
        if (autoLogin.getString("id", null) == binding.mainIdEdt.text.toString()
            && autoLogin.getString("password", null) == binding.mainPassEdt.text.toString()
        ) {
            autoLogin.edit().putBoolean("autoKey",true).commit()
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }

    fun isCallback(resultCode: Int): Boolean {
        return resultCode == RESULT_OK
    }

    fun dataExists(data: Intent): Boolean {
        return data.hasExtra("name")&& data.hasExtra("id") && data.hasExtra("password")
    }

    fun setId(data : Intent){
        data.getStringExtra("id")?.let {
            viewModel.setUserId(it)
            viewModel.id.observe(this, Observer {
                binding.mainIdEdt.text = Editable.Factory.getInstance().newEditable(it)
            })
        }
    }

    fun setPassword(data : Intent){
        data.getStringExtra("password")?.let {
            viewModel.setUserPassword(it)
            viewModel.password.observe(this, Observer {
                binding.mainPassEdt.text =
                    Editable.Factory.getInstance().newEditable(it)
            })
        }
    }

    fun setName(data : Intent){
        data.getStringExtra("name")?.let {
            viewModel.setUserName(it)
        }
    }

    // sendData 이 함수 사용
    fun sendData(intent : Intent){
        intent.putExtra("name",autoLogin.getString("name",null))
        intent.putExtra("id",autoLogin.getString("id",null))
        intent.putExtra("password",autoLogin.getString("password",null))
    }

    // TODO: Refactoring More
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == viewModel.SIGN_UP_CODE) {
            if (isCallback(resultCode)) {
                if (dataExists(data!!)) {
                    setId(data)
                    setPassword(data)
                    setName(data)
                }
            }
        }
    }*/
}