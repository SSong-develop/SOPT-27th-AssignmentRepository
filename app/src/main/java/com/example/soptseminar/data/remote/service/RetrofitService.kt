package com.example.soptseminar.data.remote.service

import com.example.soptseminar.data.remote.model.DummyData
import com.example.soptseminar.data.remote.model.KakaoSearchData
import com.example.soptseminar.data.remote.model.UserInfo
import com.example.soptseminar.model.SignInUser
import com.example.soptseminar.model.User
import com.example.soptseminar.utils.MyKeyStore
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
    // 원래라면 이 2 api는 따른 service interface로 분리하는게 맞다.
    @GET("api/users")
    suspend fun getDummy(
        @Query("page") pageNum : Int
    ) : DummyData

    // kakao web search api
    @Headers("Authorization: KakaoAK 7411840c70828638540dc83a89c1c33b")
    @GET("/v2/search/web")
    suspend fun webSearch(
        @Query("query") web : String,
        @Query("page") pageNum: Int
    ) : KakaoSearchData

}