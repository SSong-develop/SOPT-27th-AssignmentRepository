package com.example.soptseminar.data.local.sharedpreference

import com.example.soptseminar.presentation.model.User

interface OnControlled {
    fun signIn(user: User): Boolean

    fun signUp(user: User)

    fun setAutoLoginKey()

    fun isValidate(user: User): Boolean

    fun getAutoLoginKey(): Boolean

    fun getUserName(): String
}