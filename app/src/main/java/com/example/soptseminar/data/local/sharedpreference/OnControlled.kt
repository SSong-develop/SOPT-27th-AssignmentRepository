package com.example.soptseminar.data.local.sharedpreference

interface OnControlled {

    fun setAutoLoginKey()

    fun getAutoLoginKey(): Boolean

    fun getUserName(): String
}