package com.example.soptseminar.data.remote.model

import com.google.gson.annotations.SerializedName

data class DummyData(
    @SerializedName("page")
    val page : Int,
    @SerializedName("per_page")
    val per_page : Int,
    @SerializedName("total")
    val total : Int,
    @SerializedName("total_pages")
    val total_pages : Int,
    @SerializedName("data")
    val dummyUser: DummyUser
)

data class DummyUser(
    @SerializedName("id")
    val id : Int,
    @SerializedName("email")
    val email : String,
    @SerializedName("first_name")
    val first_name : String,
    @SerializedName("last_name")
    val last_name : String,
    @SerializedName("avatar")
    val avatar : String
)