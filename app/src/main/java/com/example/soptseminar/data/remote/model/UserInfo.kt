package com.example.soptseminar.data.remote.model

import com.google.gson.annotations.SerializedName

data class UserInfo(
    @SerializedName("status")
    val status : Int,
    @SerializedName("success")
    val success : Boolean,
    @SerializedName("message")
    val message : String,
    @SerializedName("data")
    var userData : UserData
)