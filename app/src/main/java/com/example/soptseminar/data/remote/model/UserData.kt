package com.example.soptseminar.data.remote.model

import com.google.gson.annotations.SerializedName

data class UserData(
    @SerializedName("email")
    val email : String,
    @SerializedName("password")
    val password : String,
    @SerializedName("userName")
    val userName : String
)