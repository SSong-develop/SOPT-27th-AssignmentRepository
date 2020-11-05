package com.example.soptseminar.utils

import com.example.soptseminar.presentation.adapter.ProfileAdapter
import com.example.soptseminar.presentation.model.ProfileData

class MakeDummy {
    // dummy Data function
    // remove it when you are
    companion object{
        fun makeDummy(profileAdapter : ProfileAdapter){
            profileAdapter.data = mutableListOf(
                ProfileData("송훈기0","010-1234-1234"),
                ProfileData("송훈기1","010-1234-1235"),
                ProfileData("송훈기2","010-1234-1236"),
                ProfileData("송훈기3","010-1234-1237"),
                ProfileData("송훈기4","010-1234-1238"),
                ProfileData("송훈기5","010-1234-1239"),
                ProfileData("송훈기6","010-1234-1230"),
                ProfileData("송훈기7","010-1234-1231"),
                ProfileData("송훈기8","010-1234-1232"),
                ProfileData("송훈기9","010-1234-1233"),
                ProfileData("송훈기10","010-1234-1234"),
                ProfileData("송훈기11","010-1234-1235"),
                ProfileData("송훈기12","010-1234-1236"),
                ProfileData("송훈기13","010-1234-1237"),
                ProfileData("송훈기14","010-1234-1238"),
                ProfileData("송훈기15","010-1234-1239"),
                ProfileData("송훈기16","010-1234-1230"),
                ProfileData("송훈기17","010-1234-1231"),
                ProfileData("송훈기18","010-1234-1232"),
                ProfileData("송훈기19","010-1234-1233"),
                ProfileData("송훈기20","010-1234-1234")
            )
        }
    }
}