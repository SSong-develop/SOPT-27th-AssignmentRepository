package com.example.soptseminar.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("userName")
    var name: String,
    @SerializedName("email")
    var email: String,
    @SerializedName("password")
    var password: String
)