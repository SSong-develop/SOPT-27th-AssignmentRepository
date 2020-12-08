package com.example.soptseminar.data.remote.model

import com.google.gson.annotations.SerializedName

data class KakaoSearchData(
    @SerializedName("documents")
    val document: List<KakaoSearchDocument>
)

data class KakaoSearchDocument(
    @SerializedName("contents")
    val contents : String,
    @SerializedName("datetime")
    val datetime : String,
    @SerializedName("title")
    val title : String,
    @SerializedName("url")
    val url : String
)