package com.example.soptseminar.repository

import com.example.soptseminar.data.remote.model.UserInfo
import com.example.soptseminar.data.remote.service.RetrofitService
import com.example.soptseminar.model.SignInUser
import com.example.soptseminar.model.User

class MainRepository(
    private val retrofitService: RetrofitService
) {
    suspend fun signUp(user : User) = retrofitService.postSignUp(user)

    suspend fun signIn(signInUser: SignInUser) : UserInfo = retrofitService.postSignIn(signInUser)
}