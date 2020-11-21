package com.example.soptseminar.presentation.model

import androidx.room.PrimaryKey

data class ProfileData(
    @PrimaryKey(autoGenerate = true)
    val userId: Long,
    var title: String,
    var subTitle: String
) {
}