package com.example.soptseminar.data.remote.service

import com.example.soptseminar.data.remote.model.DummyData
import com.example.soptseminar.data.remote.model.UserInfo
import com.example.soptseminar.model.SignInUser
import com.example.soptseminar.model.User
import retrofit2.http.*

interface RetrofitService {
    // 로그인
    @Headers("Content-Type:application/json")
    @POST("users/signin")
    suspend fun postSignIn(
        @Body  signInUser: SignInUser
    ) : UserInfo

    // 회원가입
    @Headers("Content-Type:application/json")
    @POST("users/signup")
    suspend fun postSignUp(
        @Body user: User
    ) : UserInfo

    // Dummy Data Api
    @GET("api/users")
    suspend fun getDummy(
        @Query("page") pageNum : Int
    ) : DummyData

}