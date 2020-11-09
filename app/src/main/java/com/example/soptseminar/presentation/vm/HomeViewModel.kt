package com.example.soptseminar.presentation.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.soptseminar.utils.SingleLiveEvent

class HomeViewModel : ViewModel() {
    private val _isChecked = SingleLiveEvent<Any>()
    val isChecked : LiveData<Any>
        get() = _isChecked
    // HomeViewModel에서 클릭 리스너 나면은 데이터를 보관하고 있다가 DetailViewModel로 데이터를 넘겨주면 됩니다
    // 그렇겠죠?
    // TODO : Adapter and other things
    fun setLinear(){
        _isChecked.setValue(true)
    }
    fun setGrid(){
        _isChecked.setValue(false)
    }
}